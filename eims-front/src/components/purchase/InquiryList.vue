<template>

	<div id="InquiryList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/PurchaseList">询价单</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>

		<el-container style="background-color: white;padding-top: 15px;">

			<el-header style="height: 30px;">
				<el-row>

					<el-col :span="15">
						<el-input style="width: 290px;float: left;" placeholder="搜索单据编号" v-model="searchInput">
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
					  <el-form :model="screenLabel">
						
						<el-form-item label="报价状态：" label-width="120px">
							<el-radio-group v-model="screenLabel.isQuotation">
							  <el-radio label="不限"></el-radio>
							  <el-radio label="未报价"></el-radio>
							  <el-radio label="已报价"></el-radio>
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
							@click="toDetail()">新增询价单</el-button>
						
					</el-col>
				</el-row>
			</el-header>

			<el-main style="background-color: white;">
				<el-table :data="tableData" max-height="477" style="width: 100%;height:477px;" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column label="单据编号" prop="inquiryDocunum">
					</el-table-column>
					<el-table-column label="单据日期" prop="documentDate">
					</el-table-column>
					<el-table-column label="询价发起者" prop="inquirySourceName">
					</el-table-column>
					<el-table-column label="询价接受者" prop="inquiryReceiverName">
					</el-table-column>
					<el-table-column label="报价状态" prop="ename">
						<template #default="scope">
							<p v-if="tableData[scope.$index].isQuotation == 0">未报价</p>
							<p v-if="tableData[scope.$index].isQuotation == 1">已报价</p>
						</template>
					</el-table-column>
					<el-table-column fixed="right" label="操作" width="100">
						<template #default="scope">
							<el-button v-if="tableData[scope.$index].isQuotation == 0" @click="handleDelete(scope.row.empno)" type="text">报价</el-button>
						</template>
					</el-table-column>
				</el-table>

			</el-main>
			<el-footer>
				<div class="block" style="float: right;">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
						:page-sizes="[10,20,40,80]" :page-size="queryForm.pageSize"
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
		name: "InquiryList",
		data() {
			return {
				tableData: [],
				tableTotal:0,
				multipleSelection: [],
				queryForm: {
					"pageNum":1,
					"pageSize":10
				},
				searchInput:'',
				screenLabel:{
					"isQuotation":"不限"
				},
				screenDialogVisible:false,
				showDeleteButton:false,
				queryType:0/*0全部查询，1搜索查询，2筛选查询*/
			}
		},
		methods: {
			dateFormat(row,column){
				var date = row[column.property];
				if(date == undefined){return ''};
				return moment(date).format("YYYY-MM-DD HH:mm")
			},
			loadData(){
				this.axios({
					url:"http://localhost:8080/eims/inquiry",
					method:'get',
					params:this.queryForm
				}).then((response)=>{
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch((error)=>{
					
				})
			},
			handleSelectionChange(val) {
				this.multipleSelection = val
				
				if(val.length > 0)
					this.showDeleteButton = true
				else
					this.showDeleteButton = false
			},
			handleSizeChange(val){
				this.queryForm.pageSize = val
				
				if(this.queryType == 0)
					this.loadData()
				else if(this.queryType == 1)
					this.handleSearch()
				else if(this.queryType == 2)
					this.handleScreen()
			},
			handleCurrentChange(val){
				this.queryForm.pageNum = val
				
				if(this.queryType == 0)
					this.loadData()
				else if(this.queryType == 1)
					this.handleSearch()
				else if(this.queryType == 2)
					this.handleScreen()
			},
			handleDelete(){
				this.$confirm('此操作将永久删除文件，是否继续？','提示',{
					confirmButtonTest:'确定',
					cancelButtonTest:'取消',
					type:'warning'
				}).then(()=>{
					var ids = new Array()
					for(let i=0;i<this.multipleSelection.length;i++)
						ids.push(this.multipleSelection[i].purchReturnId)
					
					this.axios({
						url:"http://localhost:8080/eims/inquiry/batch",
						method:"delete",
						data:ids
					}).then(response=>{
						this.loadData()
						this.$message({
							type:'success',
							message:'删除成功'
						});
					}).catch(error=>{
						
					})
				}).catch(()=>{
					this.$message({
						type:'info',
						message:'已取消操作'
					})
				})	
				
			},
			handleSearch(){
				this.queryType = 1
				
				this.queryForm = {"pageNum":1,"pageSize":10,"workPointId":1}
				this.queryForm.inquiryDocunum = this.searchInput
				
				this.axios({
					url:"http://localhost:8080/eims/inquiry/search",
					method:"get",
					params:this.queryForm
				}).then(response=>{
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error=>{
					
				})
			},
			handleScreen(){
				this.queryType = 2
				
				if(this.screenLabel.isQuotation=="不限")
					delete this.queryForm.isQuotation
				else if(this.screenLabel.isQuotation=="未报价")
					this.queryForm.isQuotation = 0
				else if(this.screenLabel.isQuotation=="已报价")
					this.queryForm.isQuotation = 1
				
				this.axios({
					url:"http://localhost:8080/eims/inquiry/screen",
					method:"get",
					data:this.queryForm
				}).then(response=>{
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error=>{
					
				})
				
				this.screenDialogVisible = false
			},
			toDetail() {
				this.$router.push({
					name: 'Inquiry'
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

<!-- <template>

	<div id="InquiryList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/InquiryList">询价单列表</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>

		<el-container style="background-color: white;padding-top: 15px;">

			<el-header style="height: 30px;">
				<el-row>

					<el-col :span="12">
						<el-input style="width: 290px;float: left;" placeholder="搜索单据编号" v-model="input">
							<template #append>
								<el-button icon="el-icon-search" size="small"></el-button>
							</template>
						</el-input>

					</el-col>

					<el-col :span="12">
						<el-button style="float: right;" icon="el-icon-plus" size="medium" type="primary"
							@click="toDetail()">新增询价单</el-button>
					</el-col>
				</el-row>

			</el-header>

			<el-main style="background-color: white;">

				<el-table :data="tableData" style="width: 100%;height:487px;" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column label="单据编号" prop="empno">
					</el-table-column>
					<el-table-column label="单据日期" prop="empno">
					</el-table-column>
					<el-table-column label="询价发起者" prop="empno">
					</el-table-column>
					<el-table-column label="询价接受者" prop="empno">
					</el-table-column>
					<el-table-column label="报价状态" prop="ename">
					</el-table-column>
					<el-table-column fixed="right" label="操作" width="100">
						<template #default="scope">
							<el-button @click="handleDelete(scope.row.empno)" type="text">报价</el-button>
						</template>
					</el-table-column>
				</el-table>

			</el-main>
			<el-footer>
				<div class="block" style="float: right;">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
						:page-sizes="[10,20,40,80]" :page-size="pageInfo.pageSize"
						layout="total, sizes, prev, pager, next, jumper" :total="pageInfo.total">
					</el-pagination>
				</div>
			</el-footer>
		</el-container>

	</div>

</template>

<script>
	import qs from 'qs'

	export default {
		name: "InquiryList",
		data() {
			return {
				tableData: [],
				pageInfo: {
					pageNum: 1,
					pageSize: 10,
					total: ''
				},
				multipleSelection: []
			}
		},
		methods: {
			loadData(){
				this.axios({
					url:"http://localhost:8080/eims/inquiry",
					method:'get',
					params:this.queryForm
				}).then((response)=>{
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch((error)=>{
					
				})
			},
			toDetail() {
				this.$router.push({
					name: 'Inquiry'
				})
			},
			handleSelectionChange() {

			}
		},created(){
			this.loadData()
		}
	}
</script>
 -->