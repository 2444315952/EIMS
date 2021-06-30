<template>
	<div id="addStorage">
		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/Storage">入库单列表</a></el-breadcrumb-item>
				<el-breadcrumb-item>入库单</el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>
		<el-container style="background-color#F9FAFC;padding-top: 15px;">
			<el-main style="background-color:#F9FAFC">
				<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
					<el-row>
						<el-col :span="11">
							<el-form-item label="单据编号:" prop="warehouseDocunum">
								<el-input v-model="ruleForm.warehouseDocunum" :disabled="true"
									style="float: left; width: 250px;"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="单据日期:" prop="documentDate">
								<el-date-picker type="datetime" placeholder="选择日期" v-model="ruleForm.documentDate"
									:disabled="isdisabled" style="width:250px; float: left;"></el-date-picker>
							</el-form-item>
						</el-col>
						<el-col class="line" :span="7"></el-col>
						<!-- <el-col class="line" :span="2"></el-col> -->
					</el-row>
					<el-row>
						<el-col :span="11">
							<el-form-item label="入库类型:" prop="storageType">
								<el-select v-model="ruleForm.storageType" placeholder="请选择" :disabled="isdisabled"
									@change="ruleForm.warehousingDetailList=[{}];ruleForm.warehouseName=''"
									style="width: 250px;float: left;">
									<el-option label="采购单" value="采购单"></el-option>
									<el-option label="退货售单" value="退货售单"></el-option>
								</el-select>
								<el-button icon="el-icon-thumb" size="large" style="float: left;"
									@click="selectChange()" :disabled="isdisabled">
								</el-button>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="入库仓库" style="float:left;" prop="warehouseName">
								<el-input v-model="ruleForm.warehouseName" style="width:250px;" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small" :disabled="isStop"
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
						<el-col :span="7">
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="1"></el-col>
						<el-col :span="22">
							<el-table ref="multipleTable" :data="ruleForm.warehousingDetailList" :height="tableHeight"
								tooltip-effect="dark" style="width: 100%; text-align :center; height:230px;"
								size="medium" max-height="230" @selection-change="handleSelectionChange">
								<el-table-column label="操作" width="85">
									<template #default="scope">
										<el-button icon="el-icon-remove" size="mini" :disabled="isdisabled"
											@click="deleteRow(scope.$index,Product)" circle>
										</el-button>
									</template>
								</el-table-column>
								<el-table-column label="产品名称" prop="productName" width="200">
								</el-table-column>
								<el-table-column label="产品图片" width="160">
									<template #default="scope">
										<img v-if="typeof(this.ruleForm.warehousingDetailList[0].productId) != 'undefined'"
											:src="scope.row.productPicture" style="width: 40px; height: 40px;"
											class="productPicture" />
									</template>
								</el-table-column>
								<el-table-column prop="specModel" label="产品规格" width="160">
								</el-table-column>
								<el-table-column prop="productUnit" label="单位" width="160">
								</el-table-column>
								<el-table-column label="数量" prop="inventoryQuantity" width="160" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="备注" width="350" prop="commodityNote" show-overflow-tooltip>
									<template #default="scope">
										<el-input v-model="ruleForm.warehousingDetailList[scope.$index].commodityNote"
											:disabled="isdisabled"></el-input>
									</template>

								</el-table-column>
							</el-table>
						</el-col>
						<el-col :span="1"></el-col>
					</el-row>
					<br />
					<el-row>
						<el-form-item label="单据备注:" prop="documentsNote">
							<el-input type="textarea" v-model="ruleForm.documentsNote" :disabled="isdisabled">
							</el-input>
						</el-form-item>
						<el-col :span="5"></el-col>
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
								<el-button @click="$router.push({name:'Storage'})">取消</el-button>
							</el-form-item>
						</el-col>
						<el-col :span="4"></el-col>
						<el-col :span="7">
							<el-form-item label="驳回原因:" class="YS" prop="reason" v-if="ruleForm.audited==2">
								<span class="ReasonClass" >{{ruleForm.reason}}</span>
							</el-form-item>
						</el-col>
					</el-row>

				</el-form>
				<el-dialog title="采购单" v-model="SelectPurchase" :width="'800px'">
					<el-row>
						<el-col :span="14"></el-col>
						<el-col :span="10">
							<el-input class="inline-input" placeholder="采购单号/供应商/仓库/审核状态" @keyup.enter.native="search"
								style="width: 280px; padding-bottom: 6px;" v-model="searchInput" size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" @click="search"></el-button>
								</template>
							</el-input>
						</el-col>
					</el-row>
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<!-- <el-col :span="1"></el-col> -->
						<el-col :span="24">
							<el-table :data="Purchase" tooltip-effect="dark"
								style="width:100%; height:240px; text-align: center;" max-height="240"
								highlight-current-row @current-change="currentChange">
								<el-table-column label="采购单号" width="160" prop="purchDocunum">
								</el-table-column>
								<el-table-column label="供应商名" prop="supplierName" width="110" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="仓库名" prop="warehouseName" width="110" show-overflow-tooltip>
								</el-table-column>
								<el-table-column prop="inStorage" label="是否入库" width="110" show-overflow-tooltip>
									<template #default="scope">
										<p v-if="Purchase[scope.$index].inStorage==0">未入库</p>
										<p v-if="Purchase[scope.$index].inStorage==1">已入库</p>
									</template>
								</el-table-column>
								<el-table-column label="退货状态" width="100" show-overflow-tooltip>
									<template #default="scope">
										<p v-if="Purchase[scope.$index].returnState==0">未退货</p>
										<p v-if="Purchase[scope.$index].returnState==1">部分退货</p>
										<p v-if="Purchase[scope.$index].returnState==2">全部退货</p>
									</template>
								</el-table-column>
								<el-table-column label="审核状态" prop="audited" width="100" show-overflow-tooltip>
									<template #default="scope">
										<p v-if="Purchase[scope.$index].audited==0">未审核</p>
										<p v-if="Purchase[scope.$index].audited==1">已审核</p>
									</template>
								</el-table-column>
								<el-table-column prop="employeeName" label="业务员" width="100" show-overflow-tooltip>
								</el-table-column>
							</el-table>
						</el-col>
						<!-- <el-col :span="1"></el-col> -->
					</el-row>
					<el-row>
						<el-col :span="5"></el-col>
						<el-col :span="14">
							<el-pagination :current-page="PurchaseList.queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
								:page-size="PurchaseList.queryForm.pageSize"
								layout=" total, sizes, prev, pager, next, jumper" :total="PurchaseList.queryForm.total"
								@size-change="handleSizeChange" @current-change="handleCurrentChange" />
						</el-col>
						<el-col :span="5"></el-col>
					</el-row>
					<template #footer>
						<span class="dialog-footer" style="margin-left:400px;">
							<el-button @click="SelectPurchase = false">取 消</el-button>
							<el-button type="primary" @click="AddPurchase">确定</el-button>
						</span>
					</template>
				</el-dialog>
				<el-dialog title="销售退货单" v-model="SelectSales" :width="'800px'">
					<el-row>
						<el-col :span="14"></el-col>
						<el-col :span="10">
							<el-input class="inline-input" placeholder="退货单号/客户名/仓库/审核状态" style="width: 280px;"
								@keyup.enter.native="ReturnSear" v-model="SalesInput" size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" @click="ReturnSear"></el-button>
								</template>
							</el-input>
						</el-col>
					</el-row>

					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<el-col :span="24">
							<el-table :data="Sales" tooltip-effect="dark" ref="SalesTable"
								style="width:100%; height:240px; text-align: center;" max-height="240"
								highlight-current-row @current-change="SalesSelectionChange">
								<el-table-column label="退货单号" width="170" prop="sellReturnDocunum">

								</el-table-column>
								<el-table-column label="客户名" prop="customerName" width="100" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="仓库名" prop="warehouseName" width="100" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="入库状态" prop="inStorage" width="100" show-overflow-tooltip>
									<template #default="scope">
										<p v-if="Sales[scope.$index].inStorage==0">未入库</p>
										<p v-if="Sales[scope.$index].inStorage==1">已入库</p>
									</template>
								</el-table-column>
								<el-table-column label="审核状态" prop="autited" width="90" show-overflow-tooltip>
									<template #default="scope">
										<p v-if="Sales[scope.$index].autited==0">未审核</p>
										<p v-if="Sales[scope.$index].autited==1">已审核</p>
									</template>
								</el-table-column>
								<el-table-column property="employeeName" label="操作人" width="100" show-overflow-tooltip>
								</el-table-column>
								<el-table-column property="retSellRemark" label="备注" width="130" show-overflow-tooltip>
								</el-table-column>
							</el-table>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="5"></el-col>
						<el-col :span="14">
							<el-pagination :current-page="queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
								:page-size="queryForm.pageSize" layout=" total, sizes, prev, pager, next, jumper"
								:total="queryForm.total" @size-change="SalesSizeChange"
								@current-change="SalesCurrentChange" />
						</el-col>
						<el-col :span="5"></el-col>
					</el-row>
					<template #footer>
						<span class="dialog-footer">
							<el-button @click="SelectSales = false">取 消</el-button>
							<el-button type="primary" @click="AddSales">确定</el-button>
						</span>
					</template>
				</el-dialog>
			</el-main>
		</el-container>
	</div>
</template>


<script>
	import qs from 'qs'
	export default {
		data() {
			return {
				SalesRow: null,
				isAdd: true,
				currentRow: null,
				multipleSelection: [],
				SalesInput: '',
				searchInput: '',
				isStop: false,
				isdisabled: false,
				Product: [],
				Sales: [],
				Purchase: [],
				PurchaseList: {
					queryForm: {
						pageNum: 1,
						pageSize: 2,
						total: ''
						//workPointId: 1
						//currentPage,pagesize:对应后端的
					},
				},
				SelectList: [],
				employeeList: [],
				TypeList: [],
				SelectSales: false,
				SelectPurchase: false,
				ruleForm: {
					reason: '',
					warehousingDetailList: [{}],
					storageType: '',
					warehouseDocunum: '',
					commodityNote: '',
					documentDate: '',
					delivery: false,
					warehouseName: '',
					documentsNote: '',
					employeeName: '',
				},
				tableData: [{
					number: '2',
					foldWarehouse: '默认仓',
					SpecModel: '深圳分店',
					Unit: '个',
					name: '王小虎',
					address: '已入库'
				}],
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
				queryType: 'all',
				wareRow: null,
				docuNumSequence: '',
				rules: {
					warehouseDocunum: [{
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
					storageType: [{
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
			setSales(row) {
				this.$refs.SalesTable.setCurrentRow(row);
			},
			setCurrent(row) {
				this.$refs.singleTable.setCurrentRow(row);
			},
			selectWarehouse(val) {

			},
			selectEmployee(val) {

			},
			//销售退货单当前页
			SalesCurrentChange(val) {
				this.loading = true
				this.queryForm.pageNum = val
				if (this.queryType == 'all') {
					this.ReturnsList()
				} else if (this.queryType == 'returns')
					this.ReturnSear()
			},
			//销售退货单页大小
			SalesSizeChange(val) {
				this.loading = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all') {
					this.ReturnsList()
				} else if (this.queryType == 'returns')
					this.ReturnSear()
			},
			//采购单当前页
			handleCurrentChange(val) {
				this.loading = true
				this.PurchaseList.queryForm.pageNum = val
				if (this.queryType == 'all') {
					this.BuyerList()
				} else if (this.queryType == 'search')
					this.search()
			},
			//采购单页大小
			handleSizeChange(val) {
				this.loading = true
				this.PurchaseList.queryForm.pageSize = val
				if (this.queryType == 'all') {
					this.BuyerList()
				} else if (this.queryType == 'search')
					this.search()
			},
			currentChange(val) {
				this.currentRow = val
			},

			SalesSelectionChange(val) {
				this.SalesRow = val
			},
			//生成单据编号，和时间
			getDocuDate(prefix) {
				const nowDate = new Date()
				var month = nowDate.getMonth() + 1

				month = month > 10 ? month : '0' + month
				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date
				this.ruleForm.warehouseDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"
				this.axios({
					url: 'http://localhost:8089/eims/warehouseWarrant/search',
					method: 'get',
					params: {
						'warehouseDocunum': this.ruleForm.warehouseDocunum
					}
				}).then(res => {
					console.log(res)
					const docuNumSequence = (Array(5).join(0) + (res.data.list.length + 1)).slice(-5)
					this.ruleForm.warehouseDocunum = this.ruleForm.warehouseDocunum + docuNumSequence
				}).catch(err => {

				})
			},
			openWarehouseDialog() {
				if (this.ruleForm.storageType == "采购单") {
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
				if (this.wareRow == null) {
					this.$message({
						type: 'info',
						message: '请选择入库仓库'
					});
				} else {
					this.ruleForm.warehouseId = this.warehouse.singleSelection.warehouseId
					this.ruleForm.warehouseName = this.warehouse.singleSelection.warehouseName
					this.warehouse.dialogVisible = false
				}
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
			//下拉框取值的时候绑定仓库的id
			selectWarehouse() {
				this.SelectList.forEach(w => {
					if (w.warehouseName == this.ruleForm.warehouseName)
						this.ruleForm.warehouseId = w.warehouseId
				})
			},
			// //获取下拉入库仓库的值
			queryWarehouse() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/warehouse'
				}).then(res => {
					console.log(res)
					this.SelectList = res.data.list
				}).catch(err => {
					console.log(err)
				})
			},
			//选择采购单入库
			AddPurchase() {
				//console.log(this.ruleForm)
				if (this.currentRow == null) {
					this.$message({
						type: 'info',
						message: '请选择要入库的采购单'
					});
				} else {
					//alert(this.currentRow.purchId)

					this.axios({
						url: "http://localhost:8089/eims/purchase/one",
						method: 'get',
						params: {
							id: this.currentRow.purchId
						}
					}).then(res => {
						console.log("产品为：")
						console.log(res)
						this.ruleForm.warehousingDetailList = res.data.purchaseDetailList
						this.ruleForm.warehouseId = res.data.warehouseId
						this.ruleForm.warehouseName = res.data.warehouseName
						this.detailPurchToWarehousing()
					}).catch(err => {

					})
					
					this.axios({
					url:"http://localhost:8089/eims/purchase",
					method:'put',
					data:{
						"purchId":this.currentRow.purchId,
						"inStorage":1
					}
					}).then(res=>{
						console.log("入库信息：")
						console.log(res)
					}).catch(err=>{
						
					})
					this.SelectPurchase = false
				}
			},
			//选择销售退货单入库
			AddSales() {
				//console.log(this.ruleForm)
				if (this.SalesRow == null) {
					this.$message({
						type: 'info',
						message: '请选择要入库的销售退货单'
					});
				} else {
					this.axios({
						url: "http://localhost:8089/eims/sellReturn/one",
						method: 'get',
						params: {
							id: this.SalesRow.sellReturnId
						}
					}).then(res => {
						console.log("详情数据为：'")
						console.log(res)
						this.ruleForm.warehousingDetailList = res.data.returnBillsProductList
						this.ruleForm.warehouseId = res.data.warehouseId
						this.ruleForm.warehouseName = res.data.warehouseName
						this.detailSaleToWarehousing()
					}).catch(err => {

					})
					
					this.axios({
					url:"http://localhost:8089/eims/sellReturn",
					method:'put',
					data:{
						"sellReturnId":this.SalesRow.sellReturnId,
						"inStorage":1
					}
					}).then(res=>{
						console.log("入库信息：")
						console.log(res)
					}).catch(err=>{
						
					})
					this.SelectSales = false
				}
			},
			//采购单的搜索框查询
			search() {
				this.queryType = 'search'
				this.queryForm.audited = this.searchInput
				if (this.queryForm.audited == "已审核")
					this.queryForm.audited = 1
				else if (this.queryForm.audited == "未审核")
					this.queryForm.audited = 0
				this.PurchaseList.queryForm.purchDocunum = this.searchInput
				this.PurchaseList.queryForm.supplierName = this.searchInput
				this.PurchaseList.queryForm.warehouseName = this.searchInput
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/purchase/searchInStorage',
					params: this.PurchaseList.queryForm
				}).then(res => {
					this.PurchaseList.queryForm.total = res.data.total
					this.Purchase = res.data.list
					console.log(res)
				}).catch(err => {
					console.log(err)
				})
			},
			//退货售单的搜索框查询
			ReturnSear() {
				this.queryType = 'returns'
				this.queryForm.customerName = this.SalesInput
				this.queryForm.sellReturnDocunum = this.SalesInput
				this.queryForm.warehouseName = this.SalesInput
				this.queryForm.audited = this.SalesInput
				if (this.queryForm.audited == "已审核")
					this.queryForm.audited = 1
				else if (this.queryForm.audited == "未审核")
					this.queryForm.audited = 0
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/sellReturn/searchInStorage',
					params: this.queryForm
				}).then(res => {
					this.queryForm.total = res.data.total
					this.Sales = res.data.list
					console.log(res)
				}).catch(err => {

				})
			},
			//根据选中下拉框的值弹出相应对应框
			selectChange() {
				if (this.ruleForm.storageType == '采购单') {
					this.SelectPurchase = true
					this.BuyerList()
				} else if (this.ruleForm.storageType == '退货售单') {
					this.isStop = true
					this.SelectSales = true
					this.ReturnsList()
				} else {
					this.SelectPurchase = false
					this.SelectSales = false
				}

			},

			// selectType(){
			// 	this.ruleForm.warehousingDetailList=[{}]
			// },

			//查询采购单表格中的数据
			BuyerList() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/purchase/screen',
					params: {
						"inStorage": 0,
						"pageNum": this.PurchaseList.queryForm.pageNum,
						"pageSize": this.PurchaseList.queryForm.pageSize
					}
				}).then(res => {
					console.log(res)
					this.PurchaseList.queryForm.total = res.data.total
					this.Purchase = res.data.list
				}).catch(err => {

				})
			},
			//查询退货售单表格中的数据
			ReturnsList() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/sellReturn/screen',
					params: {
						"inStorage": 0,
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize
					}
				}).then(res => {
					this.queryForm.total = res.data.total
					this.Sales = res.data.list
					//console.log(res)
				})

			},
			detailSaleToWarehousing() {
				var totalQuantity = 0
				this.ruleForm.warehousingDetailList.forEach(detail => {
					detail.warehouseDocunum = this.ruleForm.warehouseDocunum
					detail.inventoryQuantity = detail.sellQuantity
					detail.specModel = detail.productModel

					totalQuantity = totalQuantity + detail.sellQuantity
					//totalQuantity += detail.purchaseQuantity
				})
				this.ruleForm.inventoryQuantity = totalQuantity
				//console.log(this.ruleForm.warehousingDetailList)
			},
			detailPurchToWarehousing() {
				var totalQuantity = 0
				this.ruleForm.warehousingDetailList.forEach(detail => {
					detail.warehouseDocunum = this.ruleForm.warehouseDocunum
					detail.inventoryQuantity = detail.purchaseQuantity
					detail.commodityNote = this.ruleForm.commodityNote

					totalQuantity = totalQuantity + detail.purchaseQuantity
					//totalQuantity += detail.purchaseQuantity
				})
				this.ruleForm.inventoryQuantity = totalQuantity
				//console.log(this.ruleForm.warehousingDetailList)
			},
			//保存新增入库单数据
			submitForm(formName) {
				const list = this.ruleForm.warehousingDetailList
				for (var i = 0; i < list.length; i++) {
					if (typeof(list[i].productId) == "undefined") {
						this.$message({
							type: 'warning',
							message: '请选择要入库的产品'
						})
						return false
					}
				}
				//this.detailPurchToWarehousing()
				//console.log(this.ruleForm.warehousingDetailList)
				this.$refs[formName].validate((valid) => {
					if (valid) {
						if (this.isAdd) {
							this.axios({
								method: 'post',
								url: 'http://localhost:8089/eims/warehouseWarrant',
								data: this.ruleForm
							}).then(res => {
								//console.log(res)
								this.$message({
									type: 'success',
									message: '入库单数据新增成功！'
								})
								this.$router.push({
									name: 'Storage'
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
								url: 'http://localhost:8089/eims/warehouseWarrant',
								data: this.ruleForm
							}).then(res => {
								this.$message({
									type: 'success',
									message: '入库单信息更改成功！'
								})
								this.$router.push({
									name: 'Storage'
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
			deleteRow(index, rows) {
				if (rows.length > 0) { //若删除的行是表格的仅存的最后一行,则不能删除
					rows.splice(index, 1);
				}
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			ViewDetails() {

			},
			//查看详情
			show() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/warehouseWarrant/one',
					params: {
						"id": this.$route.params.warehouseWarrantId
					}
				}).then(res => {
					this.ruleForm = res.data
					this.ruleForm.warehousingDetailList = res.data.warehousingDetailList
					console.log(res)
					if (this.ruleForm.audited == 1) {
						// this.isStop=true
						this.isdisabled = true
					} else {
						this.isdisabled = false
					}
				})
			}
		},
		created() {
			//判断是新增还是修改还是查看详情操作
			console.log(this.$route.params.warehouseWarrantId)
			this.ruleForm.warehouseWarrantId = this.$route.params.warehouseWarrantId
			this.isAdd = typeof(this.ruleForm.warehouseWarrantId) == "undefined" || this.ruleForm.warehouseWarrantId == ""
			if (this.isAdd)
				this.getDocuDate("RKD")
			else {
				console.log("查看详情")
				this.show()
			}

		}
	}
</script>
<style>
	#addStorage {
		/* background-color: aliceblue; */
	}
	
	#addStorage .ReasonClass{
		font-size: 26px;
		color: #ff0000;
	}
	
	
	/* #addStorage .YS{
		font-size: 28px;
		color: #000000;
	} */

	#addStorage .el-container {
		height: 100%;
		width: 100%;
	}

	#addStorage .el-header,
	#addStorage .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	#addStorage .el-aside {
		background-color: #D3DCE6;
		color: #333;
	}

	#addStorage .el-main {
		background-color: #E9EEF3;
		color: #333;
		height: 100%;
	}


	#addStorage .el-row {
		margin-bottom: 5px;
	}

	#addStorage .el-col {
		border-radius: 4px;
	}

	#addStorage .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

	#addStorage .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	/* .el-input__inner{
			width: 200px;
		} */
	#addStorage .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}

	#addStorage .el-dialog__body {
		padding: 5px;
	}

	#addStorage .el-table {
		padding: 0px 15px;
	}

	#addStorage .cell {
		padding-left: 0px;
	}

	#addStorage .el-table td,
	#addStorage .el-table th {
		padding: 5px 0px;
	}

	#addStorage .el-dialog .el-table td,
	#addStorage .el-dialog .el-table th {
		padding: 2px 0px;
	}

	/* #addStorage .el-table td,
	#addStorage .el-table th {
		padding: 0 1px;
	} */
</style>
