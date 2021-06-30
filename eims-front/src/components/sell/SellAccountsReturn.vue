<template>

	<div id="PurchaseList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/sellbill">往来账务</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>
		<el-tabs v-model="activeName" @tab-click="handleClick">
		<el-tab-pane label="销售付款" name="first" >
		
		</el-tab-pane>
		
		
		
		
		
		
		<el-tab-pane label="退货回款" name="second">
			
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
								<el-form-item label="入库状态：" label-width="120px">
									<el-radio-group v-model="screenCondition.inStorage">
										<el-radio>不限</el-radio>
										<el-radio label="0">未入库</el-radio>
										<el-radio label="1">已入库</el-radio>
									</el-radio-group>
								</el-form-item>
			
								<el-form-item label="审核状态：" label-width="120px">
									<el-radio-group v-model="screenCondition.autited">
										<el-radio>不限</el-radio>
										<el-radio label="0">未审核</el-radio>
										<el-radio label="1">已审核</el-radio>
									</el-radio-group>
			
								</el-form-item>
			
								<el-form-item label="退款状态：" label-width="120px">
									<el-radio-group v-model="screenCondition.refunded">
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
						<el-table-column label="退货日期" prop="sellReturnHirthday" width="135" :formatter="dateFormat">
						</el-table-column>
						<el-table-column label="单据生成日期" prop="documentDate" width="135" :formatter="dateFormat">
						</el-table-column>
						<el-table-column label="单据编号" width="162" prop="sellReturnDocunum">
							
						</el-table-column>
						<el-table-column label="关联销售单编号" width="162" prop="sellDocunum">
						<template #default="scope">
							<router-link :to="{name:'selldetail',params:{sellId:scope.row.sellId}}">
								{{scope.row.sellDocunum}}
							</router-link>
						</template>
						</el-table-column>
			
			
						<el-table-column label="业务员" prop="employeeName">
						</el-table-column>
						<el-table-column label="入库状态">
							<template #default="scope">
								<p v-if="tableData[scope.$index].inStorage == 0">未入库</p>
								<p v-if="tableData[scope.$index].inStorage == 1">已入库</p>
							</template>
						</el-table-column>
			
						<el-table-column label="审核状态" prop="autited">
							<template #default="scope">
								<p v-if="tableData[scope.$index].autited == 0">未审核</p>
								<p v-if="tableData[scope.$index].autited == 1">已审核</p>
							</template>
						</el-table-column>
						<el-table-column label="退款状态" prop="autited">
							<template #default="scope">
								<p v-if="tableData[scope.$index].refunded == 0">未退款</p>
								<p v-if="tableData[scope.$index].refunded == 1">部分退款</p>
								<p v-if="tableData[scope.$index].refunded == 2">全部退款</p>
								<p v-if="tableData[scope.$index].refunded ==3">退款溢出</p>
							</template>
						</el-table-column>
						
			
						
						<el-table-column label="交易金额" prop="retPayAmount"></el-table-column>
						
						<el-table-column label="退款金额" prop="retPaidAmount"></el-table-column>
						
						<el-table-column label="欠款" prop="qiankuan">
			
						</el-table-column>
			
						<el-table-column fixed="right" label="操作" width="100" fixed="right">
							<template #default="scope">
								<el-button v-if="tableData[scope.$index].refunded == 0 " @click="handleAudit(scope.row),payeebut.fukuandag=true"
								 type="text">退款</el-button>
								 <el-button v-if="tableData[scope.$index].refunded == 1 " @click="handleAudit(scope.row),payeebut.fukuandag=true"
								  type="text">退款</el-button>
								  <el-button v-if="tableData[scope.$index].refunded == 3 " @click="handleAudit(scope.row),payeebut.fukuandag=true"
								   type="text">收款</el-button>
								   <el-button v-if="tableData[scope.$index].refunded == 2 && tableData[scope.$index].retPayAmount-tableData[scope.$index].retPaidAmount!=0 " @click="handleAudit(scope.row),payeebut.fukuandag=true"
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
							<el-form-item label="退款单号:" label-width="110px" prop="sellDocunum">{{gatherCondition.gatherDocunum}}</el-form-item>
						</el-col>
					</el-row>
			
					<el-row>
						<el-col :span="6" :offset="2">
							<el-form-item label="应退金额:" label-width="110px" prop="qiankuan">{{gatherCondition.qiankuan}}</el-form-item>
						</el-col>
						<el-col :span="12" :offset="4">
							<el-form-item label="退货单单号:" label-width="110px" prop="sellDocunum">{{gatherCondition.sellReturnDocunum}}</el-form-item>
						</el-col>
					</el-row>
			
					<el-row>
						<el-col :span="6" :offset="2">
							<el-form-item label="退款金额:" label-width="110px" prop="gatherAmount">
								<el-input v-model="gatherCondition.gatherAmount" size="medium" ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12" :offset="4">
							<el-form-item label="退款日期:" label-width="110px" prop="documentDate">
								<el-date-picker type="datetime" placeholder="选择日期" size="medium" v-model="gatherCondition.documentDate" style="width: 206px;"></el-date-picker>
							</el-form-item>
						</el-col>
					</el-row>
			
					<el-row>
						<el-col :span="6" :offset="2">
							<el-form-item label="退款账户:" label-width="110px" prop="account_name">
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
				 activeName: 'second',
				isAdd: true,
				checked1: false,
				tableData: [],
				tableTotal: 0,
				
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
					"sellReturnDocunum": this.searchInput,
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
					if(tab.props.label==="销售付款"){
					this.$router.push({
						name: 'sellaccounts'
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
					this.gatherCondition.sellType=1
					this.gatherCondition.sellDocunum=this.gatherCondition.sellReturnDocunum
					this.gatherCondition.sellId=this.gatherCondition.sellReturnId
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
					 detail.qiankuan = detail.retPayAmount - detail.retPaidAmount
				})
			},

			dataselect() {

				var searchForm = Object.assign(this.searchDate, this.pageParam)
				console.log(searchForm)
				console.log(this.pageParam)
				this.axios({
					url: "http://localhost:8089/eims/sellReturn/search",
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
				url: "http://localhost:8089/eims/sellReturn/search",
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
				this.gatherCondition.gatherAmount = -this.gatherCondition.qiankuan
				
				console.log("bill" + this.gatherCondition)
				this.getDocuNum("TH")



			},
			handleDelete() {
				var isHaveAudited = false

				for (var i = 0; i < this.multipleSelection.length; i++) {

					if (this.multipleSelection[i].autited == 1) {
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
					url: "http://localhost:8089/eims/sellReturn/search",
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
					url: "http://localhost:8089/eims/sellReturn/screen",
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
