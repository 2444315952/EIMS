<template>

	<div id="PurchaseReturnList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/PurchaseReturnList">销售退货单列表</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>

		<el-container style="background-color: white;padding-top: 15px;">

			<el-header style="height: 30px;">
				<el-row>

					<el-col :span="15">
						<el-input style="width: 290px;float: left;" placeholder="搜索单据编号/客户/仓库/业务员" v-model="searchInput">
							<template #append>
								<el-button icon="el-icon-search" size="small" @click="handleSearch"></el-button>
							</template>
						</el-input>

					</el-col>

					<el-col :span="3">
						<el-button v-show="showDeleteButton" style="float: right;" size="medium" type="primary"
							@click="handleDelete()">删除</el-button>
					</el-col>

					<el-col :span="2">
						<el-button style="float: right;" size="medium" type="primary"
							@click="screenDialogVisible = true">筛选</el-button>
					</el-col>

					<el-dialog title="筛选条件" v-model="screenDialogVisible">
					  <el-form :model="screenCondition">
						
						<el-form-item label="审核状态：" label-width="120px">
							<el-radio-group v-model="screenCondition.audited">
							  <el-radio>不限</el-radio>
							  <el-radio label="0">未审核</el-radio>
							  <el-radio label="1">已审核</el-radio>
							</el-radio-group>
						</el-form-item>
						
						<el-form-item label="退款状态：" label-width="120px">
							<el-radio-group v-model="screenCondition.inRefund">
							  <el-radio>不限</el-radio>
							  <el-radio label="0">未退款</el-radio>
							  <el-radio label="1">已退款</el-radio>
							</el-radio-group>
						</el-form-item>
					  </el-form>
					  <template #footer>
					    <span class="dialog-footer">
					      <el-button @click="screenDialogVisible = false">取 消</el-button>
					      <el-button type="primary" @click="handleScreen()">确 定</el-button>
					    </span>
					  </template>
					</el-dialog>

					<el-col :span="4">
						
						<el-button style="float: right;" icon="el-icon-plus" size="medium" type="primary"
							@click="toDetail()">新增销售退货单</el-button>
						
					</el-col>
				</el-row>
			</el-header>

			<el-main style="background-color: white;">
				<el-table :data="tableData" max-height="477" style="width: 100%;height:477px;" @selection-change="handleSelectionChange">
					<el-table-column type="selection">
					</el-table-column>
					<el-table-column label="单据编号" prop="purchReturnDocunum">
					</el-table-column>
					<el-table-column label="单据日期" prop="documentDate" width="135" :formatter="dateFormat">
					</el-table-column>
					<el-table-column label="销售单号" prop="purchDocunum">
					</el-table-column>
					<el-table-column label="客户" prop="supplierName">
					</el-table-column>
					<el-table-column label="业务员" prop="employeeName">
					</el-table-column>
					<el-table-column label="仓库" prop="warehouseName">
					</el-table-column>
					<el-table-column label="审核状态" prop="audited">
						<template #default="scope">
							<p v-if="tableData[scope.$index].audited == 0">未审核</p>
							<p v-if="tableData[scope.$index].audited == 1">已审核</p>
						</template>
					</el-table-column>
					<el-table-column label="退款状态" prop="inRefund">
						<template #default="scope">
							<p v-if="tableData[scope.$index].inRefund == 0">未退款</p>
							<p v-if="tableData[scope.$index].inRefund == 1">已退款</p>
						</template>
					</el-table-column>
					<el-table-column label="交易金额" prop="transactionAmount">
					</el-table-column>
					<el-table-column label="退款金额" prop="refundAmount">
					</el-table-column>
					<el-table-column fixed="right" label="操作" width="100">
						<template #default="scope">
							<el-button v-if="tableData[scope.$index].audited == 0" @click="handleAudit(scope.row.purchReturnId)" type="text">审核</el-button>
						</template>
					</el-table-column>
				</el-table>

			</el-main>
			<el-footer>
				<div class="block" style="float: right;">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
						:page-sizes="[10,20,40,80]" :page-size="pageParam.pageSize"
						layout="total, sizes, prev, pager, next, jumper" :total="tableTotal">
					</el-pagination>
				</div>
			</el-footer>
		</el-container>

	</div>

</template>

<script>
	import qs from 'qs'
	import moment from 'moment'

	export default {
		name: "PurchaseReturnList",
		data() {
			return {
				tableData: [],
				tableTotal: 23,
				multipleSelection: [],
				pageParam: {
					"pageNum": 1,
					"pageSize": 10
				},
				searchInput: '',
				screenCondition: {
				
				},
				screenDialogVisible: false,
				showDeleteButton: false,
				queryType: 0 /*0全部查询，1搜索查询，2筛选查询*/
			}
		},
		computed: {
			searchCondition() {
				return {
					"purchDocunum": this.searchInput,
					"supplierName": this.searchInput,
					"warehouseName": this.searchInput,
					"employeeName": this.searchInput
				}
			}
		},
		methods: {
			dateFormat(row, column) {
				var date = row[column.property];
				if (date == undefined) {
					return ''
				};
				return moment(date).format("YYYY-MM-DD HH:mm")
			},
			loadData() {
				this.axios({
					url: "http://localhost:8089/eims/purchaseReturn",
					method: 'get',
					params: this.pageParam
				}).then((response) => {
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch((error) => {
			
				})
			},
			handleSelectionChange(val) {
				this.multipleSelection = val
			
				if (val.length > 0)
					this.showDeleteButton = true
				else
					this.showDeleteButton = false
			},
			handleSizeChange(val) {
				this.pageParam.pageSize = val
			
				if (this.queryType == 0)
					this.loadData()
				 else if (this.queryType == 1)
					this.handleSearch()
				else if (this.queryType == 2)
					this.handleScreen()
			},
			handleCurrentChange(val) {
				this.pageParam.pageNum = val
			
				if (this.queryType == 0)
					this.loadData()
				else if (this.queryType == 1)
					this.handleSearch()
				else if (this.queryType == 2)
					this.handleScreen()
			},
			handleAudit(val) {
				this.$confirm('此操作将通过审核，是否继续？', '提示', {
					confirmButtonTest: '确定',
					cancelButtonTest: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						url: "http://localhost:8089/eims/purchaseReturn",
						method: "put",
						data: {
							"purchId": val,
							"audited": 1
						}
					}).then(response => {
						this.loadData()
						this.$message({
							type: 'success',
							message: '审核成功'
						})
					}).catch(error => {
			
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消操作'
					})
				})
			
			},
			handleDelete() {
				var isHaveAudited = false
				this.multipleSelection.forEach(m => {
					if (m.audited == 1) {
						isHaveAudited = true
						return false
					}
				})
			
				if (isHaveAudited) {
					this.$message({
						type: 'info',
						message: '无法删除已审核的数据'
					})
					return false
				}
			
				this.$confirm('此操作将永久删除文件，是否继续？', '提示', {
					confirmButtonTest: '确定',
					cancelButtonTest: '取消',
					type: 'warning'
				}).then(() => {
					var ids = new Array()
					for (let i = 0; i < this.multipleSelection.length; i++)
						ids.push(this.multipleSelection[i].purchId)
			
					this.axios({
						url: "http://localhost:8089/eims/purchaseReturn/batch",
						method: "delete",
						data: ids
					}).then(response => {
						this.loadData()
						this.$message({
							type: 'success',
							message: '删除成功'
						});
					}).catch(error => {
			
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消操作'
					})
				})
			
			},
			handleSearch() {
			
				this.queryType = 1
			
				var searchForm = Object.assign(this.searchCondition,this.pageParam)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/purchaseReturn/search",
					method: "get",
					params: searchForm
				}).then(response => {
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error => {
			
				})
			},
			handleScreen() {
				this.queryType = 2
			
				var screenForm = Object.assign(this.screenCondition,this.pageParam)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/purchaseReturn/screen",
					method: "get",
					params: screenForm
				}).then(response => {
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error => {
			
				})
			
				this.screenDialogVisible = false
			},
			toDetail() {
				this.$router.push({
					name: 'sellreturndetail'
				})
			}
		},created(){
			this.loadData()
		}
	}
</script>

<style>

	#PurchaseList .el-table .cell .el-button{
		padding: 0px;
		min-height:22px;
		height: 22px;
	}
	
</style>