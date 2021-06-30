<template>
	<div id="addOdo">
		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/Odo">出库单列表</a></el-breadcrumb-item>
				<el-breadcrumb-item><a href="/">出库单</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>
		<el-container style="background-color#F9FAFC;padding-top: 15px;">
			<el-main style="background-color:#F9FAFC">
				<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
					<el-row>
						<el-col :span="11">
							<el-form-item label="单据编号:" prop="stockOutDocunum">
								<el-input v-model="ruleForm.stockOutDocunum" :disabled="true"
									style="float: left; width: 250px;"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="单据日期:" prop="documentDate">
								<el-date-picker type="datetime" placeholder="选择日期" v-model="ruleForm.documentDate"
									:disabled="isdisabled" style="width:250px; float: left;"></el-date-picker>
							</el-form-item>
						</el-col>
						<el-col class="line" :span="6"></el-col>
					</el-row>
					<el-row>
						<el-col :span="11">
							<el-form-item label="出库类型:" prop="outboundType">
								<el-select v-model="ruleForm.outboundType" placeholder="请选择" :disabled="isdisabled"
									@change="ruleForm.outboundDetailList=[{}];ruleForm.warehouseName=''" style="width: 250px;float: left;">
									<el-option label="销售单" value="销售单"></el-option>
									<el-option label="采购退货单" value="采购退货单"></el-option>
								</el-select>
								<el-button icon="el-icon-thumb" size="large" style="float: left;" @click="selectList()"
									:disabled="isdisabled">
								</el-button>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="出库仓库" style="float: left;" prop="warehouseName">
								<el-input v-model="ruleForm.warehouseName" style="width: 250px;" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="openWarehouseDialog()"></el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>

						<el-dialog title="仓库" v-model="warehouse.dialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="warehouse.searchInput" placeholder="请搜索仓库名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" @click="warehouseLoadData()" size="mini">
											</el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="warehouse.tableData" max-height="286" style="height: 286px;"
								highlight-current-row @current-change="warehouseSelectionChange">
								<el-table-column property="warehouseName" label="仓库名称"></el-table-column>
								<el-table-column property="employeeName" label="联系人"></el-table-column>
								<el-table-column property="phone" label="联系电话"></el-table-column>
								<el-table-column property="warehouseAddress" label="联系地址"></el-table-column>
							</el-table>
							<el-row>
								<el-col :span="24">
									<el-pagination style="float: right;margin-top: 15px;"
										@size-change="warehouseSizeChange" @current-change="warehouseCurrentChange"
										:page-sizes="[10,20,40,80]" :page-size="warehouse.pageParam.pageSize"
										layout="total, sizes, prev, pager, next, jumper" :total="warehouse.tableTotal">
									</el-pagination>
								</el-col>
							</el-row>

							<template #footer>
								<span class="dialog-footer">
									<el-button @click="warehouse.dialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="warehouseConfirmButton" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>
						<el-col :span="6">
						</el-col>
					</el-row>
					<br />
					<el-row>
						<el-col :span="1"></el-col>
						<el-col :span="22">
							<el-table ref="multipleTable" :data="ruleForm.outboundDetailList" :height="tableHeight"
								tooltip-effect="dark" style="width:100%; text-align :center; height:230px;"
								max-height="230" @selection-change="handleSelectionChange">
								<!-- 								<el-table-column label="操作" width="85">
									<template #default="scope">
										<el-button icon="el-icon-remove" size="mini" :disabled="isdisabled"
											@click="deleteRow(scope.$index,Product)" circle>
										</el-button>
									</template>
								</el-table-column> -->
								<el-table-column label="产品名称" prop="productName" width="200">
								</el-table-column>
								<el-table-column label="产品图片" width="165">
									<template #default="scope">
										<img v-if="typeof(this.ruleForm.outboundDetailList[0].productId) != 'undefined'"
											:src="scope.row.productPicture" style="width: 60px; height: 45px;"
											class="productPicture" />
									</template>
								</el-table-column>
								<el-table-column prop="specModel" label="产品规格" width="165">
								</el-table-column>
								<el-table-column prop="productUnit" label="单位" width="165">
								</el-table-column>
								<el-table-column label="数量" width="165" prop="deliveryQuantity" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="备注" prop="commodityNote" width="410" show-overflow-tooltip>
									<template #default="scope">
										<el-input v-model="ruleForm.outboundDetailList[scope.$index].commodityNote"
											:disabled="isdisabled"></el-input>
									</template>
								</el-table-column>
							</el-table>
						</el-col>
						<el-col :span="1"></el-col>
					</el-row>
					<br />
					<br />
					<el-row>
						<el-form-item label="单据备注:" prop="documentsNote">
							<el-input type="textarea" v-model="ruleForm.documentsNote" :disabled="isdisabled">
							</el-input>
						</el-form-item>
						<el-col :span="1"></el-col>
						<el-col :span="7">
							<el-form-item label="业务员:" prop="employeeName">
								<el-select v-model="ruleForm.employeeName" @change="selectEmployee"
									:disabled="isdisabled" placeholder="请选择" @click="queryEmployee()"
									style="float:left; width: 250px;">
									<el-option v-for="item in employeeList" :label="item.employeeName"
										:value="item.employeeName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<br />
					<el-row>
						<el-col :span="8"></el-col>
						<el-col :span="5">
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
								<el-button @click="$router.push({name:'Odo'})">取消</el-button>
							</el-form-item>
						</el-col>
						<el-col :span="4"></el-col>
						<el-col :span="7">
							<el-form-item label="驳回原因:" prop="reason" v-if="ruleForm.audited==2">
								<span class="ReasonClass" >{{ruleForm.reason}}</span>
							</el-form-item>
						</el-col>
					</el-row>
					
				</el-form>
				<el-dialog title="销售单" v-model="SelectSalaOrder" :width="'800px'">
					<el-row>
						<el-col :span="14"></el-col>
						<el-col :span="10">
							<el-input class="inline-input" placeholder="销售单号/客户名/仓库/业务员" @keyup.enter.native="SalaSear"
								style="width: 280px; padding-bottom: 6px;" v-model="searchInput" size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" @click="SalaSear"></el-button>
								</template>
							</el-input>
						</el-col>
					</el-row>
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<!-- <el-col :span="1"></el-col> -->
						<el-col :span="24">
							<el-table :data="SalesOrder" tooltip-effect="dark" ref="SalasTable"
								style="width:100%; height:240px; text-align: center;" max-height="240"
								highlight-current-row @current-change="currentChange">
								<el-table-column label="销售单号" width="160" prop="sellDocunum">
								</el-table-column>
								<el-table-column label="客户名" prop="customerName" width="110" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="仓库名" prop="warehouseName" width="110" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="开单时间" :formatter="dateFormat" prop="sellDate" width="120"
									show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="审核状态" prop="audited" width="90" show-overflow-tooltip>
									<template #default="scope">
										<p v-if="SalesOrder[scope.$index].audited==0">未审核</p>
										<p v-if="SalesOrder[scope.$index].audited==1">已审核</p>
									</template>
								</el-table-column>
								<el-table-column prop="employeeName" label="业务员" width="100" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="备注" width="100" prop="sellRemark" show-overflow-tooltip>
								</el-table-column>
							</el-table>
						</el-col>
						<!-- <el-col :span="1"></el-col> -->
					</el-row>
					<el-row>
						<el-col :span="5"></el-col>
						<el-col :span="14">
							<el-pagination :current-page="SalesOrderList.queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
								:page-size="SalesOrderList.queryForm.pageSize"
								layout=" total, sizes, prev, pager, next, jumper"
								:total="SalesOrderList.queryForm.total" @size-change="SalesSizeChange"
								@current-change="SalesCurrentChange" />
						</el-col>
						<el-col :span="5"></el-col>
					</el-row>
					<template #footer>
						<span class="dialog-footer" style="margin-left:400px;">
							<el-button @click="SelectSalaOrder = false">取 消</el-button>
							<el-button type="primary" @click="AddSalesOrder">确定</el-button>
						</span>
					</template>
				</el-dialog>
				<el-dialog title="采购退货单" v-model="SelPurchReturn" :width="'800px'">
					<el-row>
						<el-col :span="14"></el-col>
						<el-col :span="10">
							<el-input class="inline-input" placeholder="退单单号/供应商/仓库/业务员" style="width: 280px;"
								v-model="PurchInput" @keyup.enter.native="PurchSear" size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" @click="PurchSear"></el-button>
								</template>
							</el-input>
						</el-col>
					</el-row>
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<el-table :data="PurchReturn" tooltip-effect="dark"
							style="width: 100%; text-align: center; height: 240px;" max-height="240"
							highlight-current-row @current-change="PurchSelectionChange">
							<el-table-column label="采购退货单号" width="185" property="purchReturnDocunum">
							</el-table-column>
							<el-table-column label="单据日期" :formatter="dateFormat" prop="documentDate" width="135"
								show-overflow-tooltip>
							</el-table-column>
							<el-table-column label="供应商" width="100" property="supplierName">
							</el-table-column>
							<el-table-column label="仓库名" property="warehouseName" width="100" show-overflow-tooltip>
							</el-table-column>
							<el-table-column label="出库状态" property="isOut" width="90" show-overflow-tooltip>
								<template #default="scope">
									<p v-if="PurchReturn[scope.$index].isOut==0">未出库</p>
									<p v-if="PurchReturn[scope.$index].isOut==1">已出库</p>
								</template>
							</el-table-column>
							<el-table-column label="审核状态" width="90">
								<template #default="scope">
									<p v-if="PurchReturn[scope.$index].audited==0">未审核</p>
									<p v-if="PurchReturn[scope.$index].audited==1">已审核</p>
								</template>
							</el-table-column>
							<el-table-column property="employeeName" label="业务员" width="90" show-overflow-tooltip>
							</el-table-column>
						</el-table>
					</el-row>
					<el-row>
						<el-col :span="5"></el-col>
						<el-col :span="14">
							<el-pagination :current-page="queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
								:page-size="queryForm.pageSize" layout=" total, sizes, prev, pager, next, jumper"
								:total="queryForm.total" @size-change="PurchSizeChange"
								@current-change="PurchCurrentChange" />
						</el-col>
						<el-col :span="5"></el-col>
					</el-row>
					<template #footer>
						<span class="dialog-footer">
							<el-button @click="SelPurchReturn = false">取 消</el-button>
							<el-button type="primary" @click="AddPurchReturn">确定</el-button>
						</span>
					</template>
				</el-dialog>
			</el-main>
		</el-container>
	</div>
</template>


<script>
	import qs from 'qs'
	import moment from 'moment'
	export default {
		//name:"AddOdo",
		data() {
			return {
				SalesOrderList: {
					queryForm: {
						pageNum: 1,
						pageSize: 2,
						total: ''
						//workPointId: 1
						//currentPage,pagesize:对应后端的
					}
				},
				queryForm: {
					pageNum: 1,
					pageSize: 2,
					total: ''
					//workPointId: 1
					//currentPage,pagesize:对应后端的
				},
				warehouse: {
					dialogVisible: false,
					searchInput: '',
					tableData: [],
					tableTotal: '',
					singleSelection: {},
					pageParam: {
						"pageNum": 1,
						"pageSize": 10
					}
				},
				searchInput: '',
				SelPurchReturn: false,
				isAdd: true,
				SalesOrder: [],
				PurchReturn: [],
				SelectSalaOrder: false,
				isdisabled: false,
				SelectList: [],
				PurchInput: [],
				wareRow:null,
				employeeList: [],
				queryType: 'all',
				PurchRow: null,
				SalasRow: null,
				select: false,
				ruleForm: {
					outboundType: '',
					reason:'',
					sellRemark: '',
					outboundDetailList: [{}],
					stockOutDocunum: '',
					commodityNote: '',
					//inventoryQuantity:'',
					productName: '',
					documentDate: '',
					delivery: false,
					warehouseName: '',
					documentsNote: '',
					employeeName: '',
				},
				docuNumSequence: '',
				tableData: [{
					number: '1',
					foldWarehouse: '默认仓',
					SpecModel: '深圳分店',
					Unit: '包',
					name: '王小虎',
					address: '已作废'
				}],
				rules: {
					stockOutDocunum: [{
							required: true,
							message: '请输入单据编号',
							trigger: 'blur'
						},
						{
							min: 3,
							max: 20,
							message: '长度在 3 到 20 个字符',
							trigger: 'blur'
						}
					],
					warehouseName: [{
						required: true,
						message: '请选择入库仓库',
						trigger: 'change'
					}],
					outboundType: [{
						required: true,
						message: '请选择入库类型',
						trigger: 'change'
					}],
					documentDate: [{
						type: 'date',
						required: true,
						message: '请选择日期',
						trigger: 'change'
					}],
					employeeName: [{
						required: true,
						message: '请选择业务员',
						trigger: 'change'
					}],
				}
			};
		},

		methods: {

			setSalas(row) {
				this.$refs.SalasTable.setCurrentRow(row);
			},
			setCurrent(row) {
				this.$refs.singleTable.setCurrentRow(row);
			},
			dateFormat(row, column) {
				var date = row[column.property];
				if (date == undefined) {
					return ''
				};
				return moment(date).format("YYYY-MM-DD HH:mm")
			},

			getDocuDate(prefix) {
				const nowDate = new Date()
				var month = nowDate.getMonth() + 1

				month = month > 10 ? month : '0' + month
				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date
				this.ruleForm.stockOutDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/stockOut/search',
					params: {
						'stockOutDocunum': this.ruleForm.stockOutDocunum
					}
				}).then(res => {
					console.log(res)
					const docuNumSequence = (Array(5).join(0) + ( res.data.list.length + 1)).slice(-5)
					this.ruleForm.stockOutDocunum = this.ruleForm.stockOutDocunum + docuNumSequence
				}).catch(err => {

				})
			},
			
			openWarehouseDialog(){
				if(this.ruleForm.storageType == "销售单"){
					this.warehouse.dialogVisible = true;
					this.warehouseLoadData()
				}
			},
			
			warehouseLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/warehouse/search',
					method: 'get',
					params: Object.assign({
						'warehouseName': this.warehouse.searchInput
					}, this.warehouse.pageParam)
				}).then(response => {
					this.warehouse.tableData = response.data.list
					this.warehouse.tableTotal = response.data.total
				}).catch(error => {
			
				})
			},
			warehouseSelectionChange(val) {
				this.wareRow = val
				this.warehouse.singleSelection = val
			},
			warehouseSizeChange(val) {
				this.warehouse.pageParam.pageSize = val
				this.warehouseLoadData()
			},
			warehouseCurrentChange(val) {
				this.warehouse.pageParam.pageNum = val
				this.warehouseLoadData()
			},
			warehouseConfirmButton() {
				if(this.wareRow==null){
					this.$message({
						type: 'info',
						message: '请选择出库仓库'
					});
				}else{
					this.ruleForm.warehouseId = this.warehouse.singleSelection.warehouseId
					this.ruleForm.warehouseName = this.warehouse.singleSelection.warehouseName
					this.warehouse.dialogVisible = false
				}
			},

			currentChange(val) {
				this.SalasRow = val
			},

			PurchSelectionChange(val) {
				this.PurchRow = val
			},

			//采购退货单当前页
			PurchCurrentChange(val) {
				this.loading = true
				this.queryForm.pageNum = val
				if (this.queryType == 'all') {
					this.PurchList()
				} else if (this.queryType == 'search')
					this.PurchSear()
			},

			// //采购退货单页大小
			PurchSizeChange(val) {
				this.loading = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all') {
					this.PurchList()
				} else if (this.queryType == 'purchsearch')
					this.PurchSear()
			},

			//采购退货单搜索框查询
			PurchSear() {
				this.queryType = 'purchsearch'
				this.queryForm.employeeName = this.PurchInput
				this.queryForm.supplierName = this.PurchInput
				this.queryForm.warehouseName = this.PurchInput
				this.queryForm.purchReturnDocunum = this.PurchInput
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/purchReturn/queryByPurReturn',
					params: this.queryForm
				}).then(res => {
					console.log(res)
					this.queryForm.total = res.data.total
					this.PurchReturn = res.data.list
				}).catch(err => {

				})
			},

			// //销售单当前页
			SalesCurrentChange(val) {
				this.loading = true
				this.SalesOrderList.queryForm.pageNum = val
				if (this.queryType == 'all') {
					this.SaOrderList()
				} else if (this.queryType == 'search')
					this.SalaSear()
			},

			//销售单页大小
			SalesSizeChange(val) {
				this.loading = true
				this.SalesOrderList.queryForm.pageSize = val
				if (this.queryType == 'all') {
					this.SaOrderList()
				} else if (this.queryType == 'search')
					this.SalaSear()
			},

			//销售单搜索框查询
			SalaSear() {
				this.queryType = 'search'
				this.SalesOrderList.queryForm.sellDocunum = this.searchInput
				this.SalesOrderList.queryForm.customerName = this.searchInput
				this.SalesOrderList.queryForm.warehouseName = this.searchInput
				this.SalesOrderList.queryForm.employeeName = this.searchInput
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/sellBill/search',
					params: this.SalesOrderList.queryForm
				}).then(res => {
					console.log(res)
					this.SalesOrderList.queryForm.total = res.data.total
					this.SalesOrder = res.data.list
				})

			},

			//选择销售单出库
			AddSalesOrder() {
				if (this.SalasRow == null) {
					this.$message({
						type: 'info',
						message: '请选择要出库的销售单'
					});
				} else {
					this.axios({
						method: 'get',
						url: 'http://localhost:8089/eims/sellBill/one',
						params: {
							id: this.SalasRow.sellId
						}
					}).then(res => {
						console.log(res)
						this.ruleForm.outboundDetailList = res.data.sellDetails
						this.detailSalesOrderToStockOut()
						this.ruleForm.warehouseId=res.data.warehouseId
						this.ruleForm.warehouseName = res.data.warehouseName
					}).catch(err => {

					})
					
					this.axios({
					url:"http://localhost:8089/eims/sellBill",
					method:'put',
					data:{
						"sellId":this.SalasRow.sellId,
						"outStorage":1
					}
					}).then(res=>{
						console.log("出库信息：")
						console.log(res)
					}).catch(err=>{
						
					})
					this.SelectSalaOrder = false
				}
			},

			//选择采购退货单进行出库操作
			AddPurchReturn() {
				if (this.PurchRow == null) {
					this.$message({
						type: 'info',
						message: '请选择要入库的采购退货单'
					});
				} else {
					this.axios({
						method: 'get',
						url: 'http://localhost:8089/eims/purchaseReturn/one',
						params: {
							id: this.PurchRow.purchReturnId
						}
					}).then(res => {
						console.log(res)
						this.ruleForm.outboundDetailList = res.data.purchaseDetailList
						this.ruleForm.warehouseId=res.data.warehouseId
						this.ruleForm.warehouseName = res.data.warehouseName
						this.detailPurReturnToStockOut()
					}).catch(err => {

					})
					
					this.axios({
					url:"http://localhost:8089/eims/purchaseReturn",
					method:'put',
					data:{
						"purchReturnId":this.PurchRow.purchReturnId,
						"outStorage":1
					}
					}).then(res=>{
						console.log("出库信息：")
						console.log(res)
					}).catch(err=>{
						
					})
					this.SelPurchReturn = false
				}
			},
			//把采购退货单的数据赋值给出库单中没有的
			detailPurReturnToStockOut() {
				var totalQuantity = 0
				this.ruleForm.outboundDetailList.forEach(detail => {
					detail.stockOutDocunum = this.ruleForm.stockOutDocunum
					//detail.inventoryQuantity = detail.purchaseQuantity
					detail.commodityNote = this.ruleForm.commodityNote
					detail.deliveryQuantity = detail.purchaseQuantity

					totalQuantity = totalQuantity + detail.purchaseQuantity
				})
				this.ruleForm.deliveryQuantity = totalQuantity
			},

			detailSalesOrderToStockOut() {
				var totalQuantity = 0
				this.ruleForm.outboundDetailList.forEach(detail => {
					detail.stockOutDocunum = this.ruleForm.stockOutDocunum
					detail.deliveryQuantity = detail.sellQuantity
					detail.specModel = detail.productModel
					detail.commodityNote = this.ruleForm.commodityNote

					detail.deliveryQuantity = detail.sellQuantity

					totalQuantity = totalQuantity + detail.sellQuantity
				})
				this.ruleForm.deliveryQuantity = totalQuantity
			},

			//选中下拉框的值弹出对应的弹框
			selectList() {
				if (this.ruleForm.outboundType == '销售单') {
					this.SelectSalaOrder = true
					this.SaOrderList()
				} else if (this.ruleForm.outboundType == '采购退货单') {
					this.SelPurchReturn = true
					this.PurchList()
				} else {
					this.SelPurchReturn = false
					this.SelectSalaOrder = false
				}
			},

			//查询所有销售单的数据
			SaOrderList() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/sellBill',
					params: {
						"pageNum": this.SalesOrderList.queryForm.pageNum,
						"pageSize": this.SalesOrderList.queryForm.pageSize
					}
				}).then(res => {
					console.log(res)
					this.SalesOrderList.queryForm.total = res.data.total
					this.SalesOrder = res.data.list
				})

			},

			//查询采购退货单的数据
			PurchList() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/purchaseReturn/screen',
					params: {
						"isOut": 0,
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize
					}
				}).then(res => {
					console.log(res)
					this.queryForm.total = res.data.total
					this.PurchReturn = res.data.list
				})

			},

			//下拉查询业务员的值
			queryEmployee() {
				if (this.employeeList.length > 0)
					return false
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/employee',
				}).then(res => {
					console.log(res)
					this.employeeList = res.data.list
				}).catch(err => {

				})
			},

			//下拉框取值的时候绑定业务员的id
			selectEmployee() {
				this.employeeList.forEach(e => {
					if (e.employeeName == this.ruleForm.employeeName)
						this.ruleForm.employeeId = e.employeeId
				})
			},

			//下拉框取值的时候绑定仓库的值
			selectWarehouse(val) {
				this.SelectList.forEach(w => {
					if (w.warehouseName == this.ruleForm.warehouseName)
						this.ruleForm.warehouseId = w.warehouseId
				})
			},

			//下拉框查询所有仓库
			queryWarehouse() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/warehouse'
				}).then(res => {
					this.SelectList = res.data.list
				}).catch(err => {
					console.log(err)
				})
			},

			//点击保存新增入库单
			submitForm(formName) {
				const list=this.ruleForm.outboundDetailList
				for(var i=0;i<list.length;i++){
					if(typeof(list[i].productId)=="undefined"){
						this.$message({
							type: 'warning',
							message: '请选择要出库的产品'
						})
						return false
					}
				}
				this.$refs[formName].validate((valid) => {
					if (valid) {
						if (this.isAdd) {
							this.axios({
								method: 'post',
								url: 'http://localhost:8089/eims/stockOut',
								data: this.ruleForm
							}).then(res => {
								console.log(res)
								this.$message({
									type: 'success',
									message: '出库单数据新增成功！'
								})
								this.$router.push({
									name: 'Odo'
								})
							}).catch(err => {

							})
						} else {
							if (this.ruleForm.audited == 1) {
								this.$message({
									type: 'info',
									message: '已审核的数据无法更改'
								})
								this.show()
								return false
							}
							this.axios({
								method: 'put',
								url: 'http://localhost:8089/eims/stockOut',
								data: this.ruleForm
							}).then(res => {
								this.$message({
									type: 'success',
									message: '入库单信息更改成功！'
								})
								this.$router.push({
									name: 'Odo'
								})
							}).catch(err => {

							})
						}
					} else {
						return false;
					}
				});
			},

			//删除表格行
			// deleteRow(index, rows) {
			// 	if (rows.length > 1) { //若删除的行是表格的仅存的最后一行,则不能删除
			// 		rows.splice(index, 1);
			// 	}
			// },

			resetForm(formName) {
				this.$refs[formName].resetFields();
			},

			ViewDetails() {

			},

			//查看详情
			show() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/stockOut/one',
					params: {
						"id": this.$route.params.stockOutId
					}
				}).then(res => {
					this.ruleForm = res.data
					this.ruleForm.outboundDetailList = res.data.outboundDetailList
					console.log(res)
					if (this.ruleForm.audited == 1) {
						this.isdisabled = true
					} else {
						this.isdisabled = false
					}

				})
			}
		},

		created() {
			//判断是新增还是修改还是查看详情操作
			console.log(this.$route.params.stockOutId)
			this.ruleForm.stockOutId = this.$route.params.stockOutId
			this.isAdd = typeof(this.ruleForm.stockOutId) == "undefined" || this.ruleForm.stockOutId == ""
			if (this.isAdd) {
				this.getDocuDate("CKD")
			} else {
				this.show()
			}
		}
	}
</script>
<style>
	#addOdo {
		/* background-color: aliceblue; */
	}

	#addOdo .el-container {
		height: 100%;
		width: 100%;
	}

	#addOdo .el-header,
	#addOdo .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	#addOdo .el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}

	#addOdo .el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		height: 100%;

	}

	#addOdo .el-container {
		background-color: #E9EEF3;
		border: 1px solid rgb(235, 238, 245)
	}

	#addOdo .el-row {
		margin-bottom: 5px;
	}

	#addOdo .el-col {
		border-radius: 4px;
	}

	#addOdo .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}
	
	#addOdo .ReasonClass{
		font-size: 26px;
		color: #ff0000;
	}

	#addOdo .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	/* .el-input__inner{
			width: 200px;
		} */
	#addOdo .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}

	#addOdo .el-dialog__body {
		padding: 5px;
	}

	#addOdo .el-table td,
	#addOdo .el-table th {
		padding: 6px 0;
	}
</style>
