<template>

	<div id="PurchaseList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/sellbill">销售单列表</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>

		<el-container style="background-color: white;padding-top: 15px;">

			<el-header style="height: 30px;">
				<el-row>

					<el-col :span="16">
						<el-input style="width: 290px;float: left;" placeholder="搜索单据编号/客户/仓库/业务员"
							v-model="searchInput">
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
							<el-form-item label="出库状态：" label-width="120px">
								<el-radio-group v-model="screenCondition.outStorage">
									<el-radio>不限</el-radio>
									<el-radio label="0">未出库</el-radio>
									<el-radio label="1">已出库</el-radio>
								</el-radio-group>
							</el-form-item>

							<el-form-item label="审核状态：" label-width="120px">
								<el-radio-group v-model="screenCondition.audited">
									<el-radio>不限</el-radio>
									<el-radio label="0">未审核</el-radio>
									<el-radio label="1">已审核</el-radio>
									<el-radio label="3">已驳回</el-radio>
								</el-radio-group>
							
							</el-form-item>

							<el-form-item label="付款状态：" label-width="120px">
								<el-radio-group v-model="screenCondition.received">
									<el-radio>不限</el-radio>
									<el-radio label="0">未付款</el-radio>
									<el-radio label="1">部分付款</el-radio>
									<el-radio label="2">全部付款</el-radio>
									
								</el-radio-group>
							</el-form-item>

							<el-form-item label="退货状态：" label-width="120px">
								<el-radio-group v-model="screenCondition.returnState">
									<el-radio>不限</el-radio>
									<el-radio label="0">未退货</el-radio>
									<el-radio label="1">部分退货</el-radio>
									<el-radio label="2">全部退货</el-radio>
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

					<el-col :span="3">

						<el-button style="float: right;" icon="el-icon-plus" size="medium" type="primary"
							@click="toDetail()">新增销售单</el-button>

					</el-col>
				</el-row>
			</el-header>

			<el-main style="background-color: white;">
				<el-table :data="tableData" max-height="477" style="width: 100%;height:477px;"
					@selection-change="handleSelectionChange">
					<el-table-column type="selection">
					</el-table-column>
					
					<el-table-column label="单据编号" width="162">
						<template #default="scope">
							<router-link :to="{name:'selldetail',params:{sellId:scope.row.sellId}}">
								{{scope.row.sellDocunum}}
							</router-link>
						</template>
					</el-table-column>
					<el-table-column label="单据日期" prop="sellHirthday" width="135" :formatter="dateFormat">
					</el-table-column>
					<el-table-column label="客户" prop="customerName">
					</el-table-column>
					<el-table-column label="业务员" prop="employeeName">
					</el-table-column>
					<el-table-column label="出库状态">
						<template #default="scope">
							<p v-if="tableData[scope.$index].outStorage == 0">未出库</p>
							<p v-if="tableData[scope.$index].outStorage == 1">已出库</p>
						</template>
					</el-table-column>
					<el-table-column label="仓库" prop="warehouseName">
					</el-table-column>
					<el-table-column label="审核状态" prop="audited">
						<template #default="scope">
							<p v-if="tableData[scope.$index].audited == 0">未审核</p>
							<p v-if="tableData[scope.$index].audited == 1">已审核</p>
							<p v-if="tableData[scope.$index].audited == 3">已驳回</p>
						</template>
					</el-table-column>
					
					<el-table-column label="付款状态" prop="received">
						<template #default="scope">
							<p v-if="tableData[scope.$index].received == 0">未付款</p>
							<p v-if="tableData[scope.$index].received == 1">部分付款</p>
							<p v-if="tableData[scope.$index].received == 2">全部付款</p>
							<p v-if="tableData[scope.$index].received == 3">付款溢出</p>
						</template>
					</el-table-column>
					<el-table-column label="退货状态" prop="returnState">
						<template #default="scope">
							<p v-if="tableData[scope.$index].returnState == 0">未退货</p>
							<p v-if="tableData[scope.$index].returnState == 1">部分退货</p>
							<p v-if="tableData[scope.$index].returnState == 2">全部退货</p>
						</template>
					</el-table-column>
					<el-table-column label="交易金额" prop="billPaidAmount">
					</el-table-column>
					<el-table-column fixed="right" label="操作" width="100"  fixed="right">
						<template #default="scope">
							<el-button v-if="tableData[scope.$index].audited == 0"
								@click="handleAudit(scope.row.sellId)" type="text">审核</el-button>
								<el-button v-if="tableData[scope.$index].audited == 0"
									@click="handleAudit1(scope.row.sellId)" type="text">驳回</el-button>
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
		name: "PurchaseList",
		data() {
			return {
				checked1:false,
				tableData: [],
				tableTotal: 0,
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
				queryType: 'all'
			}
		},
		computed: {
			searchCondition() {
				return {
					"sellDocunum": this.searchInput,
					"customerName": this.searchInput,
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
					url: "http://localhost:8089/eims/sellBill/search",
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

				if (this.queryType == 'all')
					this.loadData()
				else if (this.queryType == 'search')
					this.handleSearch()
				else if (this.queryType == 'screen')
					this.handleScreen()
			},
			handleCurrentChange(val) {
				this.pageParam.pageNum = val

				if (this.queryType == 'all')
					this.loadData()
				else if (this.queryType == 'search')
					this.handleSearch()
				else if (this.queryType == 'screen')
					this.handleScreen()
			},
			handleAudit(val) {
				console.log(val)
				this.$confirm('此操作将通过审核，是否继续？', '提示', {
					confirmButtonTest: '确定',
					cancelButtonTest: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						url: "http://localhost:8089/eims/sellBill",
						method: "put",
						data: {sellId:val,audited:1}
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
			handleAudit1(val) {
				console.log(val)
				this.$confirm('此操作将通过审核，是否继续？', '提示', {
					confirmButtonTest: '确定',
					cancelButtonTest: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						url: "http://localhost:8089/eims/sellBill",
						method: "put",
						data: {sellId:val,audited:3}
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
				
				for(var i=0;i<this.multipleSelection.length;i++){
					
					if(this.multipleSelection[i].audited == 1){
						this.$message({
							type:'info',
							message:'已审核的数据无法删除'
						})
						return false
					}else if(this.multipleSelection[i].inStorage == 1){
						this.$message({
							type:'info',
							message:'已入库的数据无法删除'
						})
						return false
					}else if(this.multipleSelection[i].paymentStatus == 1 || this.multipleSelection[i].paymentStatus == 2){
						this.$message({
							type:'info',
							message:'已付款的数据无法删除'
						})
						return false
					}else if(this.multipleSelection[i].returnState == 1 || this.multipleSelection[i].returnState == 2){
						this.$message({
							type:'info',
							message:'已退货的数据无法删除'
						})
						return false
					}
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
						url: "http://localhost:8089/eims/purchase/batch",
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

				this.queryType = 'search'

				var searchForm = Object.assign(this.searchCondition,this.pageParam)
				console.log(searchForm)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/sellBill/search",
					method: "get",
					params: searchForm
				}).then(response => {
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error => {

				})
			},
			handleScreen() {
				this.queryType = 'screen'

				var screenForm = Object.assign(this.screenCondition,this.pageParam)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/sellBill/screen",
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
					name: 'selldetail'
				})
			}
		},
		created() {
			this.loadData()
			
			this.axios({
				url:'http://localhost:8089/eims/warehouse',
				method:'get'
			}).then(response=>{
				console.log(response.data.list)
			}).catch(error=>{
				
			})
		}
	}
	
</script>

<style>
	#PurchaseList .el-table .cell .el-button {
		padding: 0px;
		min-height: 22px;
		height: 22px;
	}
</style>
