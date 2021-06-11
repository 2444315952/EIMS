<template>

	<div id="PaymentList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/PaymentList">付款单列表</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>

		<el-container style="background-color: white;padding-top: 15px;">

			<el-header style="height: 30px;">
				<el-row>

					<el-col :span="18">
						<el-input style="width: 290px;float: left;" placeholder="搜索单据编号/采购单号/供应商/业务员/"
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

					<el-col :span="3">

						<el-button style="float: right;" icon="el-icon-plus" size="medium" type="primary"
							@click="this.$router.push({name:'fkd'})">新增付款单</el-button>

					</el-col>
				</el-row>
			</el-header>

			<el-main style="background-color: white;">
				<el-table :data="tableData" max-height="477" style="width: 100%;height:477px;"
					@selection-change="handleSelectionChange">
					<el-table-column type="selection">
					</el-table-column>
					<el-table-column label="单据编号" width="162" prop="payDocunum">
						
					</el-table-column>
					<el-table-column label="单据日期" prop="documentDate" width="135" :formatter="dateFormat">
					</el-table-column>
					<el-table-column label="采购单号" width="162" prop="purchDocunum">
						
					</el-table-column>
					<el-table-column label="供应商" prop="supplierName">
					</el-table-column>
					<el-table-column label="业务员" prop="employeeName">
					</el-table-column>
					<el-table-column label="审核状态" prop="audited">
						<template #default="scope">
							<p v-if="tableData[scope.$index].audited == 0">未审核</p>
							<p v-if="tableData[scope.$index].audited == 1">已审核</p>
						</template>
					</el-table-column>
					<el-table-column label="付款金额" prop="paymentAmount ">
					</el-table-column>
					<el-table-column label="结算方式" prop="clearingForm">
					</el-table-column>
					<el-table-column fixed="right" label="操作" width="100">
						<template #default="scope">
							<el-button v-if="tableData[scope.$index].audited == 0"
								@click="handleAudit(scope.row.payId)" type="text">审核</el-button>
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
import { warn } from '@vue/runtime-core'

	export default {
		name: "PurchaseList",
		data() {
			return {
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
					payDocunum: this.searchInput,
					purchDocunum: this.searchInput,
					supplierName : this.searchInput,
					employeeName : this.searchInput
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
			loadData(){
				this.axios({
					url:"http://localhost:8089/eims/payment",
					method:'get',
					params:this.pageParam
				}).then((response) =>{
					this.tableData = response.data.list
					this.tableTotal = response.data.total
					console.log(response)
				}).catch((error) =>{

				}) 
			},
			handleSelectionChange(val) {
				
			},
			handleSizeChange(val) {
				this.pageParam.pageSize=val
				if(this.queryType == 'all')
					this.loadData()
				else if (this.queryType == 'search')
					this.handleSearch()
				
			},
			handleCurrentChange(val) {
				this.pageParam.pageNum = val

				if (this.queryType == 'all')
					this.loadData()
				else if (this.queryType == 'search')
					this.handleSearch()
				
			},
			handleAudit(val) {
				this.$confirm('此操作将通过审核，是否继续？','提示',{
					confirmButtonTest:'确定',
					cancelButtonTest:'取消',
					type:'warning'
				}).then(() => {
					console.log(val)
					this.axios({
						url:"http://localhost:8089/eims/payment",
						method:"put",
						data:{
							"payId":val,
							"audited":1
						}
					}).then(response => {
						this.loadData()
						this.$message({
							type:'success',
							message:'审核成功'
							
						})
					}).catch(() => {
						this.$message({
							type:'info',
							message:'已取消操作'
						})
					})
				})
			},
			handleDelete() {
				
			},
			handleSearch() {
				this.queryType = 'search'

				console.log(this.searchInput)
				console.log(this.searchCondition)
				var searchForm = Object.assign(this.searchCondition,this.pageParam)
				
				this.axios({
					url: "http://localhost:8089/eims//payment/search",
					method: "get",
					params: searchForm
				}).then(response => {
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error => {

				})
			},
			handleScreen() {
				
			},
			toDetail() {
				this.$router.push({
					name:'pi'
				})
			}
		},
		created() {
			this.loadData();
		}
	}
</script>

<style>
	#PaymentList .el-table .cell .el-button {
		padding: 0px;
		min-height: 22px;
		height: 22px;
	}
</style>
