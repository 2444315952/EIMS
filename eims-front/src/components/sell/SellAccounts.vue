<template>

	<div id="PurchaseList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/sellbill">往来账务</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>
		<el-tabs v-model="activeName" @tab-click="handleClick">
		<el-tab-pane label="销售收款" name="first">
		<el-container style="background-color: white;padding-top: 15px;">

			<el-header style="height: 30px;">
				<el-row>

					<el-col :span="16">
						<el-input style="width: 290px;float: left;" placeholder="搜索单据编号/客户/仓库/业务员" v-model="searchInput">
							<template #append>
								<el-button icon="el-icon-search" size="small" @click="handleSearch"></el-button>
							</template>

						</el-input>
						<el-button style="float: left;" size="medium" type="primary" @click="dateNum1=0,dateNum2=30,dataselect()">30天</el-button>
						<el-button style="float: left;" size="medium" type="primary" @click="dateNum1=0,dateNum2=7,dataselect()">7天</el-button>
						<el-button style="float: left;" size="medium" type="primary" @click="dateNum1=2,dateNum2=2.5,dataselect()">前天</el-button>
						<el-button style="float: left;" size="medium" type="primary" @click="dateNum1=1,dateNum2=1.5,dataselect()">昨天</el-button>
						<el-button style="float: left;" size="medium" type="primary" @click="dateNum1=0,dateNum2=0.5,dataselect()">今天</el-button>

					</el-col>

					<el-col :span="3">
						<el-button v-show="showDeleteButton" style="float: right;" size="medium" type="primary" @click="handleDelete()">删除</el-button>
					</el-col>

					<el-col :span="2">
						<el-button style="float: right;" size="medium" type="primary" @click="screenDialogVisible = true">筛选</el-button>
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

						<el-button style="float: right;" icon="el-icon-plus" size="medium" type="primary" @click="toDetail()">新增销售单</el-button>

					</el-col>
				</el-row>
			</el-header>

			<el-main style="background-color: white;">
				<el-table :data="tableData" max-height="477" style="width: 100%;height:477px;" @selection-change="handleSelectionChange">
					<el-table-column type="selection">
					</el-table-column>
					<el-table-column label="客户" prop="customerName">
					</el-table-column>
					<el-table-column label="单据日期" prop="sellHirthday" width="135" :formatter="dateFormat">
					</el-table-column>
					<el-table-column label="单据编号" width="162">
						<template #default="scope">
							<router-link :to="{name:'selldetail',params:{sellId:scope.row.sellId}}">
								{{scope.row.sellDocunum}}
							</router-link>
						</template>
					</el-table-column>


					<el-table-column label="业务员" prop="employeeName">
					</el-table-column>
					<el-table-column label="出库状态">
						<template #default="scope">
							<p v-if="tableData[scope.$index].outStorage == 0">未出库</p>
							<p v-if="tableData[scope.$index].outStorage == 1">已出库</p>
						</template>
					</el-table-column>

					<el-table-column label="审核状态" prop="audited">
						<template #default="scope">
							<p v-if="tableData[scope.$index].audited == 0">未审核</p>
							<p v-if="tableData[scope.$index].audited == 1">已审核</p>
						</template>
					</el-table-column>
					<el-table-column label="退货状态" prop="audited">
						<template #default="scope">
							<p v-if="tableData[scope.$index].returnState == 0">未退货</p>
							<p v-if="tableData[scope.$index].returnState == 1">部分退货</p>
							<p v-if="tableData[scope.$index].returnState == 2">全退货</p>
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
					<el-table-column label="交易金额" prop="billPaidAmount"></el-table-column>
					<el-table-column label="已付金额" prop="receivedMoney">

					</el-table-column>
					<el-table-column label="欠款" prop="qiankuan">

					</el-table-column>

					<el-table-column fixed="right" label="操作" width="100" fixed="right">
						<template #default="scope">
							<el-button v-if="tableData[scope.$index].received == 0 " @click="handleAudit(scope.row),payeebut.fukuandag=true"
							 type="text">收款</el-button>
							<el-button v-if="tableData[scope.$index].received == 1 " @click="handleAudit(scope.row),payeebut.fukuandag=true"
							 type="text">收款</el-button>
							 <el-button v-if="tableData[scope.$index].received == 3 " @click="handleAudit(scope.row),payeebut.fukuandag=true"
							  type="text">还款</el-button>
							  <el-button v-if="tableData[scope.$index].receivedMoney-tableData[scope.$index].billPaidAmount!=0 && tableData[scope.$index].received==2" @click="handleAudit(scope.row),payeebut.fukuandag=true"
							   type="text">补差</el-button>
							  
							   
							   
						</template>
					</el-table-column>
				</el-table>

			</el-main>
			<el-footer>
				<div class="block" style="float: right;">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,40,80]"
					 :page-size="pageParam.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="tableTotal">
					</el-pagination>
				</div>
			</el-footer>
		</el-container>
		<el-dialog title="收款单(客户 )" v-model="payeebut.fukuandag">
			<el-form :model="gatherCondition">



				<el-row>
					<el-col :span="12" :offset="4">
						<el-form-item label="客户名称：" label-width="220px" prop="customerName">:{{gatherCondition.customerName}}</el-form-item>
					</el-col>
				</el-row>
				<el-row>

					<el-col :span="12" :offset="2">
						<el-form-item label="收款单号:" label-width="110px" prop="sellDocunum">{{gatherCondition.gatherDocunum}}</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="6" :offset="2">
						<el-form-item label="欠款金额:" label-width="110px" prop="gqiankuan">{{gatherCondition.qiankuan}}</el-form-item>
					</el-col>
					<el-col :span="12" :offset="4">
						<el-form-item label="销售单号:" label-width="110px" prop="sellDocunum">{{gatherCondition.sellDocunum}}</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="6" :offset="2">
						<el-form-item label="收款金额:" label-width="110px" prop="gatherAmount">
							<el-input v-model="gatherCondition.gatherAmount" size="medium"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12" :offset="4">
						<el-form-item label="收款日期:" label-width="110px" prop="documentDate">
							<el-date-picker type="datetime" placeholder="选择日期" size="medium" v-model="gatherCondition.documentDate" style="width: 206px;"></el-date-picker>
						</el-form-item>
					</el-col>
				</el-row>

				<el-row>
					<el-col :span="6" :offset="2">
						<el-form-item label="收款账户:" label-width="110px" prop="account_name">
							<el-select @click="gatherselect()" @change="changegatherSelect" v-model="gatherCondition.accountName" style="width: 206px;"
							 placeholder="请选择业务员">
								<el-option v-for="e in moneyAccountSelectValue" :label="e.accountName" :value="e.accountName"></el-option>

							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12" :offset="4">
						<el-form-item label="经办人:" label-width="110px" prop="">
							<el-select @click="clickEmployeeSelect()" @change="changeEmployeeSelect" v-model="gatherCondition.employeeName"
							 style="width: 206px;" placeholder="请选择业务员">
								<el-option v-for="e in employeeSelectValue" :label="e.employeeName" :value="e.employeeName"></el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>





			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="payeebut.fukuandag = false">取 消</el-button>
					<el-button type="primary" @click="gratheradd('gatherCondition')">确 定</el-button>
				</span>
			</template>
		</el-dialog>
		</el-tab-pane>
		
		
		
		
		
		
		<el-tab-pane label="退货回款" name="second" >
			
			
		</el-tab-pane>
		
		<el-tab-pane label="收付款单据" name="fount" >
		
		</el-tab-pane>
	 </el-tabs>
	</div>

</template>

<script>
	import qs from 'qs'
	import moment from 'moment'

	export default {
		name: "PurchaseList",
		data() {
			return {
				 activeName: 'first',
				isAdd: true,
				checked1: false,
				tableData: [],
				tableTotal: 0,
				returnsell:{
					tableData: [],
					tableTotal: 0,
					pageParam: {
						"pageNum": 1,
						"pageSize": 10,
					
					},
				},
				multipleSelection: [],
				employeeSelectValue: [],
				moneyAccountSelectValue: [],
				pageParam: {
					"pageNum": 1,
					"pageSize": 10,

				},
				searchInput: '',
				dateNum1: '',
				dateNum2: '',
				screenCondition: {


				},

				gatherCondition: {


				},
				screenDialogVisible: false,
				showDeleteButton: false,
				payeebut: {
					fukuandag: false,
				},
				queryType: 'all'
			}
		},
		computed: {
			searchCondition() {
				return {
					"sellDocunum": this.searchInput,
					"customerName": this.searchInput,
					"warehouseName": this.searchInput,
					"employeeName": this.searchInput,

				}
			},
			searchDate() {
				return {
					"dateNumber1": this.dateNum1,
					"dateNumber2": this.dateNum2

				}
			}
		},
		methods: {
			//tbs标签页
			handleClick(tab, event) {
			        console.log(tab, event);
					console.log(tab.props.label)
					if(tab.props.label==="退货回款"){
					
					this.$router.push({
						name: 'sellaccountreturn'
					})
					}else if(tab.props.label==="收付款单据"){
					this.$router.push({
						name: 'sellbillgather'
					})
					
					}
			      },

			getDocuNum(prefix) {
				const nowDate = new Date()

				var month = nowDate.getMonth() + 1
				month = month > 10 ? month : '0' + month

				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date

				this.gatherCondition.gatherDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"

				this.axios({
					url: 'http://localhost:8089/eims/gather/search',
					method: 'get',
					params: {
						'sellOrderDocunum': this.gatherCondition.gatherDocunum
					}
				}).then(response => {
					console.log(response)
					const docuNumSequence = (Array(5).join(0) + (response.data.list.length + 1)).slice(-5)
					this.gatherCondition.gatherDocunum = this.gatherCondition.gatherDocunum + docuNumSequence
				}).catch(error => {

				})

			},
			gratheradd(formName) {
				var aa = true
				const list = this.gatherCondition


				if (typeof(list.moneyAccountId) == "undefined" || list.moneyAccountId == "") {
					this.$message({
						type: 'warning',
						message: '请选择收款账户'
					})
					aa = false
					return false
				} else if (typeof(list.gatherAmount) == "undefined" || list.gatherAmount == "") {
					this.$message({
						type: 'warning',
						message: '请填写价格'
					})
					aa = false
					return false
				} else if (typeof(list.documentDate) == "undefined" || list.documentDate == "") {
					this.$message({
						type: 'warning',
						message: '请填写付款日期'
					})
					aa = false
					return false
				}


				if (aa) {
					this.gatherCondition.sellType=0
					this.axios({
						url: 'http://localhost:8089/eims/gather',
						method: 'post',
						data: this.gatherCondition
					}).then(response => {
						this.$message({
							type: 'success',
							message: '付款单数据新增成功！'
						})
						this.payeebut.fukuandag = false
						this.loadData()


					}).catch(error => {

					})
				} else {
					return false;
				}


			},
			qiankuanselect() {
				this.tableData.forEach(detail => {
					 detail.qiankuan = detail.billPaidAmount - detail.receivedMoney
				})
			},

			dataselect() {

				var searchForm = Object.assign(this.searchDate, this.pageParam)
				console.log(searchForm)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/sellBill/search",
					method: "get",
					params: searchForm
				}).then(response => {
					this.tableData = response.data.list
					this.qiankuanselect()

					this.tableTotal = response.data.total
				}).catch(error => {

				})



			},
			dateFormat(row, column) {
				var date = row[column.property];
				if (date == undefined) {
					return ''
				};
				return moment(date).format("YYYY-MM-DD HH:mm")
			},
			// 销售单查询
			loadData() {


				this.axios({
					url: "http://localhost:8089/eims/sellBill/search",
					method: 'get',
					params: this.pageParam
				}).then((response) => {
					this.tableData = response.data.list

					this.qiankuanselect()

					console.log(response.data.list)
					this.tableTotal = response.data.total
				}).catch((error) => {

				})
			},
			
			clickEmployeeSelect() {

				if (this.employeeSelectValue.length > 0)
					return false

				this.axios({
					url: 'http://localhost:8089/eims/employee',
					method: 'get'
				}).then(response => {
					this.employeeSelectValue = response.data.list
				}).catch(error => {

				})
			},
			gatherselect() {
				if (this.moneyAccountSelectValue.length > 0)
					return false

				this.axios({
					url: 'http://localhost:8089/eims/moneyAccount',
					method: 'get'
				}).then(response => {
					this.moneyAccountSelectValue = response.data.list
					console.log(this.moneyAccountSelectValue)
				}).catch(error => {

				})


			},
			changeEmployeeSelect() {
				this.employeeSelectValue.forEach(e => {
					if (e.employeeName == this.gatherCondition.employeeName)
						this.gatherCondition.employeeId = e.employeeId
				})
			},
			changegatherSelect() {
				this.moneyAccountSelectValue.forEach(e => {
					if (e.accountName == this.gatherCondition.accountName)
						this.gatherCondition.moneyAccountId = e.moneyAccountId

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
				this.gatherCondition = val
				this.gatherCondition.gatherAmount = this.gatherCondition.qiankuan
				console.log("bill" + this.gatherCondition)
				this.getDocuNum("SH")



			},
			handleDelete() {
				var isHaveAudited = false

				for (var i = 0; i < this.multipleSelection.length; i++) {

					if (this.multipleSelection[i].audited == 1) {
						this.$message({
							type: 'info',
							message: '已审核的数据无法删除'
						})
						return false
					} else if (this.multipleSelection[i].inStorage == 1) {
						this.$message({
							type: 'info',
							message: '已入库的数据无法删除'
						})
						return false
					} else if (this.multipleSelection[i].paymentStatus == 1 || this.multipleSelection[i].paymentStatus == 2) {
						this.$message({
							type: 'info',
							message: '已付款的数据无法删除'
						})
						return false
					} else if (this.multipleSelection[i].returnState == 1 || this.multipleSelection[i].returnState == 2) {
						this.$message({
							type: 'info',
							message: '已退货的数据无法删除'
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

				var searchForm = Object.assign(this.searchCondition, this.pageParam)
				console.log(searchForm)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/sellBill/search",
					method: "get",
					params: searchForm
				}).then(response => {
					this.tableData = response.data.list
					this.qiankuanselect()
					this.tableTotal = response.data.total
				}).catch(error => {

				})
			},
			handleScreen() {
				this.queryType = 'screen'

				var screenForm = Object.assign(this.screenCondition, this.pageParam)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/sellBill/screen",
					method: "get",
					params: screenForm
				}).then(response => {
					this.tableData = response.data.list
					this.qiankuanselect()
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
				url: 'http://localhost:8089/eims/warehouse',
				method: 'get'
			}).then(response => {
				console.log(response.data.list)
			}).catch(error => {

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
