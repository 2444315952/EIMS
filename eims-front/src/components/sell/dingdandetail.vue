<template>
	<div id="Purchase">

		<el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

			<el-row>
				<el-col :span="12">
					<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
						<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
						<el-breadcrumb-item><a href="/PurchaseList">销货单列表</a></el-breadcrumb-item>
						<el-breadcrumb-item><a href="/">销售订单</a></el-breadcrumb-item>
					</el-breadcrumb>
				</el-col>

				<el-col :span="12">
					<el-button style="float: right;position: relative;bottom:8px;right: 3px;" size="medium" type="primary" @click="submitForm()">保存</el-button>
				</el-col>

			</el-row>

			<el-container style="background-color: white;padding-top: 15px;">

				<el-main style="background-color: white;">
					<el-row>
						<el-col :span="8">
							<el-form-item label="单据编号" style="float: left;" prop="purchDocunum">
								<el-input v-model="SellOrderBill.sellOrderDocunum" size="medium" disabled></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="8">
							<el-form-item label="出货日期" style="float: left;" required>
								<el-form-item prop="documentDate">
									<el-date-picker type="date" placeholder="选择日期" size="medium" v-model="SellOrderBill.sellOrderDate" style="width: 206px;"></el-date-picker>
								</el-form-item>
							</el-form-item>
						</el-col>

						<el-col :span="8">
							<el-form-item label="业务员" style="float: left;" required>
								<el-select v-model="ruleForm.employeeName" style="width: 206px;" placeholder="请选择业务员">
									<el-option label="业务员1" value="shanghai"></el-option>
									<el-option label="业务员2" value="beijing"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="客户" style="float: left;" prop="supplierName">
								<el-input v-model="kehuinput.customerName" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small" @click="supplierDialogVisible = true"></el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>

						<el-dialog title="客户" v-model="supplierDialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="supplierSearchContent" placeholder="请搜索客户名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" size="mini"></el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="kehu" max-height="300" style="height: 300px;" highlight-current-row @current-change="supplierSelectionChange">
								<el-table-column property="customerName" label="客户名称"></el-table-column>
								<el-table-column property="contact" label="联系人"></el-table-column>
								<el-table-column property="contactNumber" label="联系电话"></el-table-column>
								<el-table-column property="contactAddress" label="联系地址"></el-table-column>
							</el-table>
							<template #footer>
								<span class="dialog-footer">

									<el-button @click="supplierDialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="supplierDialogVisible = false;dingdanaddress()">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>


						<el-col :span="8">
							<el-form-item label="仓库" style="float: left;" prop="warehouseName">
								<el-input v-model="ruleForm.warehouseName" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small" @click="warehouseDialogVisible = true"></el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>

						<el-dialog title="仓库" v-model="warehouseDialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="warehouseSearchContent" placeholder="请搜索仓库名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" size="mini"></el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="warehouse" max-height="300" style="height: 300px;" @current-change="warehouseSelectionChange">
								<el-table-column type="selection" width="55">
								</el-table-column>
								<el-table-column property="warehouseName" label="仓库名称"></el-table-column>
								<el-table-column property="employeeName" label="联系人"></el-table-column>
								<el-table-column property="phone" label="联系电话"></el-table-column>
								<el-table-column property="warehouseAddress" label="联系地址"></el-table-column>
							</el-table>
							<template #footer>
								<span class="dialog-footer">
									<el-button @click="warehouseDialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="warehouseDialogVisible = false" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>

						<el-col :span="8">
							<el-form-item label="客户地址" style="float: left;" prop="warehouseName">
								<el-input v-model="SellOrderBill.sellOrderAddress" size="medium">

								</el-input>
							</el-form-item>
						</el-col>


					</el-row>

					<!-- 商品预存表 -->
					<!-- 	{{sellOrderDetails}} -->
					<!-- {{SellOrderBill.sellOrderDetails}} -->
					<el-table :data="SellOrderBill.sellOrderDetails" show-summary max-height="402" style="width: 100%;height:402px;"
					 :summary-method="getSummaries" show-summary>
						<el-table-column label="产品名称" prop="productName">
							<template #default="scope">
								<el-input v-model="SellOrderBill.sellOrderDetails[scope.$index].productName" style="width: 170px" size="small"
								 disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="mini" @click="productDialogVisible = true;productSelectButton=scope.$index;">
										</el-button>
									</template>
								</el-input>
							</template>
						</el-table-column>
						<el-table-column label="规格型号" prop="productModel">
						</el-table-column>
						<el-table-column label="产品单位" prop="productUnit">
						</el-table-column>
						<el-table-column label="销售价" prop="sellPrice">
							<template #default="scope">
								<el-input-number v-model="scope.row.sellPrice" controls-position="right" size="small" @change="uppayAmount(scope.$index)"
								 style="width: 130px;" :precision="2">
								</el-input-number>
							</template>
						</el-table-column>
						<el-table-column label="销售数量" prop="sellQuantity">
							<template #default="scope">
								<el-input-number v-model="scope.row.sellQuantity" size="small" :min="0" :precision="0" @change="uppayAmount(scope.$index)">
								</el-input-number>
							</template>
						</el-table-column>
						<el-table-column label="小计" prop="orderDetailPayAmount">
						</el-table-column>

						<el-table-column label="优惠" prop="orderDetailDiscounts">
						</el-table-column>
						<el-table-column label="操作" width="100">
							<template #default="scope">
								<el-button type="primary" icon="el-icon-plus" size="mini" @click="addRow()" circle>
								</el-button>
								<el-button type="primary" icon="el-icon-minus" size="mini" @click="removeRow(scope.$index)" circle></el-button>
							</template>
						</el-table-column>
					</el-table>

					<el-dialog title="产品" v-model="productDialogVisible">
						<el-row type="flex" justify="end" style="padding-bottom: 12px;">
							<el-col :span="7.5">
								<el-input v-model="productSearchContent" placeholder="请搜索产品名称" size="small">
									<template #append>
										<el-button icon="el-icon-search" size="mini"></el-button>
									</template>
								</el-input>
							</el-col>

						</el-row>

						<el-table ref="multipleTable" :data="proallData" max-height="300" style="height: 300px;" @selection-change="handleSelectionChange">
							<el-table-column type="selection" width="55">
							</el-table-column>
							<el-table-column property="productName" label="产品名称"></el-table-column>
							<el-table-column property="specModel" label="规格型号"></el-table-column>
							<el-table-column property="productUnit" label="产品单位"></el-table-column>
							<el-table-column property="referCost" label="参考成本价"></el-table-column>
							<el-table-column property="marketPrice" label="市场价"></el-table-column>
						</el-table>
						<div style="float: right;">
							<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageInfo.currentPage"
							 :page-sizes="[2, 3, 6, 10]" :page-size="pageInfo.pageSize" layout="total, sizes, prev, pager, next, jumper"
							 :total="pageInfo.total">
							</el-pagination>
						</div>
						<template #footer>
							<span class="dialog-footer">
								<el-button @click="productDialogVisible = false" size="medium">取 消</el-button>
								<el-button type="primary" @click="productDialogVisible = false;tablenewproduct()">确 定
								</el-button>
							</span>
						</template>
					</el-dialog>
				</el-main>

				<el-footer style="height: 106px;">

					<el-row style="margin-top: 10px;">
						<el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="SellOrderBill.sellOrderRemark">
						</el-input>
					</el-row>
					<el-row>

						<!-- 	<el-col :span="4">
							<div class="grid-content bg-purple">
								<h1>应收金额</h1>
							</div>
						</el-col> -->
						<el-col :span="4">
							<div class="grid-content bg-purple-light">
								<h1>优惠金额 <el-input v-model="SellOrderBill.orderSellDiscounts" disabled></el-input>
								</h1>
							</div>
						</el-col>
						<el-col :span="4">
							<div class="grid-content bg-purple-light">
								<h1>金额<el-input v-model="SellOrderBill.orderPayAmount" disabled></el-input>
								</h1>
							</div>
						</el-col>
					</el-row>





					<el-row>


						<!-- <el-col :span="7">
							<el-form-item label="业务员" style="float: left;" required>
								<el-select v-model="ruleForm.employeeName" style="width: 206px;" placeholder="请选择业务员">
									<el-option label="业务员1" value="shanghai"></el-option>
									<el-option label="业务员2" value="beijing"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					
					
						<el-col :span="10">
							<el-form-item label="供应商" style="float: left;" prop="supplierName">
								<el-input v-model="ruleForm.supplierName" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="supplierDialogVisible = true"></el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col> -->
					</el-row>

	</div>
	</el-footer>


	</el-container>





	</el-form>


	</div>
</template>

<script>
	export default {
		name: "Purchase",
		data() {
			return {
				isAdd: true,

				ruleForm: {
					name: "CGD-00001",
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: '',
					purchDocunum: this.docuNum,

				},

				kehuinput: [],
				temporary: [],
				sell_order_bill: [],
				multipleSelection: [],
				productSelectButton: 0,
				SellOrderBill: {
					sellOrderId: 0,
					companyId:0,
					sellOrderDocunum: '',
					sellOrderDate: '',
					audited: 0,
					workPointId: 0,
					workPointName: '工作点1',
					employeeId: 0,
					employeeName: '业务员1',
					customerId: 0,
					customerName: '',
					warehouseId: 0,
					warehouseName: '仓库1',
					orderPayAmount: 0,
					orderPaidAmount: 0,
					orderSellDiscounts: 0,
					sellOrderRemark: '',
					sellOrderAddress: '',
					sellOrderDetails: [{}],


				},


				// payAmount: 0,
				// paidAmount: 0,
				// sellDiscounts: 0,


				//  private BigDecimal orderDetailPayAmount;

				//     private BigDecimal orderDetailPaidAmount;

				//     private BigDecimal orderDetailDiscounts;
				sellOrderDetails: [
					// 	{
					// 	sellOrderDetailId:0,
					// 	sellOrderId:0,
					// 	productId:0,
					// 	productPicture:'',
					// 	productName:'',
					// 	prloductModel:'',
					// 	productUnit:'',
					// 	sellPrice:0,
					// 	sellQuantity:0,
					// 	payAmount:0,
					// 	paidAmount:0,
					// 	sellDiscounts:0,
					// 	returned:0,

					// },
				],
				pageParam: {
					"pageNum": 1,
					"pageSize": 10
				},
				pageInfo: {

					isEnabled: "",
					currentPage: 1,
					pageSize: 7,
					total: 0
				},
				pageInfoCust: {
					customerName: "",
					workPointId: 0,
					isEnabled: "",
					currentPage: 1,
					pageSize: 3,
					total: 0
				},
				kehu: [],
				tableData: [],
				proallData: [],
				rules: {
					name: [{
						required: true,
						message: '请输入活动名称',
						trigger: 'blur'
					}],
					region: [{
						required: true,
						message: '请选择活动区域',
						trigger: 'change'
					}],
					date1: [{
						type: 'date',
						required: true,
						message: '请选择日期',
						trigger: 'change'
					}],
					date2: [{
						type: 'date',
						required: true,
						message: '请选择时间',
						trigger: 'change'
					}],
					type: [{
						type: 'array',
						required: true,
						message: '请至少选择一个活动性质',
						trigger: 'change'
					}],
					resource: [{
						required: true,
						message: '请选择活动资源',
						trigger: 'change'
					}],
					desc: [{
						required: true,
						message: '请填写活动形式',
						trigger: 'blur'
					}]
				},
				tableData: [{
					'productName': '产品1',
					'specModel': '规格1',
					'productUnit': '单位1',
					'purchasePrice': 10,
					'purchaseQuantity': 2,
					purchaseSubtotal: 20
				}],
				supplier: [],
				supplierSearchContent: '',
				supplierDialogVisible: false,
				warehouse: [],
				warehouseSearchContent: '',
				warehouseDialogVisible: false,
				product: [],
				productSearchContent: '',
				productDialogVisible: false
			}
		},

		computed: {
			searchCondition() {
				return {
					"sellOrderId": this.$route.params.sellOrderId,

				}
			},
		},
		methods: {



			getSummaries(param) {
				const {
					columns,
					data
				} = param;
				const sums = [];
				columns.forEach((column, index) => {
					if (index === 0) {
						sums[index] = '总价';
						return;
					}
					const values = data.map(item => Number(item[column.property]));
					if (!values.every(value => isNaN(value))) {
						sums[index] = values.reduce((prev, curr) => {
							const value = Number(curr);
							if (!isNaN(value)) {
								return prev + curr;
							} else {
								return prev;
							}
						}, 0);

					} else {
						// sums[index] = '----';
					}
				});
				console.log("价格是" + sums[5])
				this.SellOrderBill.orderPayAmount = parseInt(sums[5])
				this.SellOrderBill.orderSellDiscounts = parseInt(sums[6])

				return sums;
			},


			dingdanaddress() {
				// SellOrderBill: {
				// 	sellOrderId: 0,
				// 	sellOrderDocunum: '',
				// 	sellOrderDate: '',
				// 	audited: 0,
				// 	workPointId: 0,
				// 	workPointName: '',
				// 	employeeId: 0,
				// 	employeeName: '',
				// 	customerId: 0,
				// 	customerName: '',
				// 	warehouseId: 0,
				// 	warehouseName: '',
				// 	payAmount: 0,
				// 	paidAmount: 0,
				// 	sellDiscounts: 0,
				// 	sellOrderRemark: '',
				// 	sellOrderAddress:'',
				// 	sellOrderDetails: [{}],


				// },


				this.kehuinput = this.temporary
				this.SellOrderBill.customerId = this.kehuinput.customerId
				this.SellOrderBill.customerName = this.kehuinput.customerName
				this.SellOrderBill.sellOrderAddress = this.kehuinput.contact + " " + this.kehuinput.contactNumber + " " + this.kehuinput
					.contactAddress + " "



			},
			// stopclear() {
			// 	console.log("aa")

			// 	           this.$refs.multipleTable.clearSelection();


			// 	},
			uppayAmount(aa) {
				console.log("优惠id"+aa)

				console.log(this.SellOrderBill.sellOrderDetails[aa].orderDetailPayAmount = this.SellOrderBill.sellOrderDetails[aa].sellPrice *
					this.SellOrderBill.sellOrderDetails[aa].sellQuantity)
				this.SellOrderBill.sellOrderDetails[aa].orderDetailDiscounts = this.SellOrderBill.sellOrderDetails[aa].sellPrice1 *
					this.SellOrderBill.sellOrderDetails[aa].sellQuantity - this.SellOrderBill.sellOrderDetails[aa].sellPrice *
					this.SellOrderBill.sellOrderDetails[aa].sellQuantity

			},

			tablenewproduct() {

				for (var aa in this.sellOrderDetails) {
					console.log(this.productSelectButton)
					console.log(parseInt(this.productSelectButton) + parseInt(aa))

					this.SellOrderBill.sellOrderDetails[parseInt(this.productSelectButton) + parseInt(aa)] = this.sellOrderDetails[aa]




				}


			},
			//保存商品信息进订单详情
			handleSelectionChange(val) {
				this.multipleSelection = [];
				this.sellOrderDetails = [];
				for (var aa in val) {
					// productId:0,
					// productPicture:'',
					// productName:'',
					// prloductModel:'',
					// productUnit:'',
					// sellPrice:0,
					// sellQuantity:0,
					// payAmount:0,
					// paidAmount:0,
					// sellDiscounts:0,
					// returned:0,
					this.sellOrderDetails.push({
						sellOrderDetailId: 0,
						sellOrderId: 0,
						productId: 0,
						productPicture: '',
						productName: '',
						productModel: '',
						productUnit: '',
						sellPrice: 0,
						sellPrice1: 0,
						sellQuantity: 1,
						orderDetailPayAmount: 0,
						orderDetailPaidAmount: 0,
						orderDetailDiscounts: 0,
						returned: 0,

					})
					console.log(val[aa].productId + "aa" + aa)
					this.multipleSelection[aa] = val[aa];
					console.log("-----" + val[aa].productId)
					this.sellOrderDetails[aa].productId = val[aa].productId
					this.sellOrderDetails[aa].productPicture = val[aa].productPicture
					this.sellOrderDetails[aa].productName = val[aa].productName
					this.sellOrderDetails[aa].productModel = val[aa].specModel
					this.sellOrderDetails[aa].productUnit = val[aa].productUnit
					this.sellOrderDetails[aa].sellPrice = val[aa].marketPrice
					this.sellOrderDetails[aa].sellPrice1 = val[aa].marketPrice

					this.sellOrderDetails[aa].orderDetailPayAmount = this.sellOrderDetails[aa].sellPrice * this.sellOrderDetails[aa].sellQuantity

					console.log(this.sellOrderDetails[aa].productId)

				}


				// this.SellOrderBill.sellOrderDetails=this.sellOrderDetails

				console.log(this.multipleSelection)
				console.log("----" + this.sellOrderDetails)
			},

			addforge() {
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					var _this = this;
					var aa = _this.multipleSelection



					_this.axios({
						method: 'post',
						url: 'http://127.0.0.1:8089/eims/product/batch',
						data: aa
					}).then(function(response) {
						// this.addshop = false
						console.log(_this.multipleSelection)
						console.log(response.data)
						console.log(_this.multipleSelection)
						window.location.reload()
					}).catch(function(error) {
						console.log(error)
					})




				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});

			},


			getProjectNum() {
				const projectTime = new Date() // 当前中国标准时间
				const Year = projectTime.getFullYear() // 获取当前年份 支持IE和火狐浏览器.
				const Month = projectTime.getMonth() + 1 // 获取中国区月份
				const Day = projectTime.getDate() // 获取几号
				var CurrentDate = Year
				if (Month >= 10) { // 判断月份和几号是否大于10或者小于10
					CurrentDate += Month
				} else {
					CurrentDate += '0' + Month
				}
				if (Day >= 10) {
					CurrentDate += Day
				} else {
					CurrentDate += '0' + Day
				}
				return CurrentDate
			},
			// getDocuNumImpl: async function(prefix) {
			// 	var purchDocunum = prefix + "-";

			// 	const nowDate = new Date()

			// 	var month = nowDate.getMonth() + 1
			// 	month = month > 10 ? month : '0' + month

			// 	var date = nowDate.getDate()
			// 	date = date > 10 ? date : '0' + date

			// 	purchDocunum = purchDocunum + nowDate.getFullYear() + month + date

			// 	this.axios({
			// 		url: 'http://localhost:8080/eims/purchase/search',
			// 		method: 'get',
			// 		params: {
			// 			'purchDocunum': purchDocunum
			// 		}
			// 	}).then(response => {
			// 		purchDocunum = purchDocunum + (response.data.list.length + 1)
			// 	}).catch(error => {

			// 	})

			// 	console.log(purchDocunum)
			// },
			supplierSelectionChange(val) {
				this.temporary = val
				console.log(val)
				return val


			},
			warehouseSelectionChange() {

			},
			productSelectionChange() {

			},
			addRow() {
				this.SellOrderBill.sellOrderDetails.push({});
			},
			removeRow(index) {
				if (this.SellOrderBill.sellOrderDetails.length > 1)
					this.SellOrderBill.sellOrderDetails.splice(index, 1);
			},
			//新增订单
			submitForm() {


				if (this.$route.params.sellOrderId == undefined || this.$route.params.sellOrderId == "") {
					this.addsellorderbillone()
				} else {
					this.updatesellorderbillone()
				}

			},
			addsellorderbillone() {
				var aa = this.SellOrderBill
				var yanzhen = false
				if (aa.customerName == null || aa.customerName == "" || aa.sellOrderDate == null || aa.sellOrderDate == "") {
					alert("必填未填")
				} else {
					aa.sellOrderDetails.forEach(e => {
						if (e.sellOrderId != null) {
							console.log(e.sellOrderId)

							yanzhen = true
						}


					})
					if (yanzhen == true) {
						var _this = this
						this.axios.post("http://127.0.0.1:8089/eims/sellOrderBill", this.SellOrderBill)
							.then(function(response) {

								console.log(response.data)
								// _this.upview()
								window.location.reload()

							}).catch(function(error) {
								console.log(error)
							})

					} else {
						alert("未选商品")


					}
				}

			},
			updatesellorderbillone() {

				var aa = this.SellOrderBill
				var yanzhen = false
				if (aa.customerName == null || aa.customerName == "" || aa.sellOrderDate == null || aa.sellOrderDate == "") {
					alert("必填未填")
				} else {
					aa.sellOrderDetails.forEach(e => {
						if (e.sellOrderId != null) {
							console.log(e.sellOrderId)

							yanzhen = true
						}


					})
					if (yanzhen == true) {
						var _this = this
						this.axios.put("http://127.0.0.1:8089/eims/sellOrderBill", this.SellOrderBill)
							.then(function(response) {

								console.log(response.data)
								_this.upview()
								window.location.reload()

							}).catch(function(error) {
								console.log(error)
							})

					} else {
						alert("未选商品")


					}
				}

			},
			handleSizeChange(size) {
				var _this = this
				this.pageInfo.pageSize = size
				// var ps=qs.stringify(this.pageInfo)
				// console.log("size:",ps)
				this.axios.get("http://127.0.0.1:8089/eims/product/xyj", {
						params: this.pageInfo
					})
					.then(function(response) {
						console.log("-------------------------------------------")
						console.log(response.data)
						_this.proallData = response.data.list
						_this.pageInfo.total = response.data.total
					}).catch(function(error) {
						console.log(error)
					})

			},
			handleCurrentChange(page) {
				var _this = this
				this.pageInfo.currentPage = page
				// var ps=qs.stringify(this.pageInfo)
				// console.log("page:",ps)
				this.axios.get("http://127.0.0.1:8089/eims/product/xyj", {
						params: this.pageInfo
					})
					.then(function(response) {
						console.log("-------------------page------------------------")
						console.log(response.data)
						_this.proallData = response.data.list

					}).catch(function(error) {
						console.log(error)
					})
			},
			sellall() {
				var _this = this
				_this.axios.get("http://127.0.0.1:8089/eims/customer/xyj", {
						params: _this.pageInfoCust
					})
					.then(function(response) {
						console.log(response.data)
						_this.kehu = response.data.list
						console.log("++++++", response.data.list)
						_this.pageInfoCust.total = response.data.total
						// _this.Customer.workPointId=_this.pageInfo.total+1
					}).catch(function(error) {
						console.log(error)
					});
			},
			sellallprotype() {

				var _this = this

				_this.axios.get("http://127.0.0.1:8089/eims/commodityType").then(function(response) {
					console.log("商品类型：", response.data)




				})

			},
			sellallpro() {
				var _this = this
				_this.axios.get("http://127.0.0.1:8089/eims/product/xyj", {
						params: _this.pageInfo
					})
					.then(function(response) {
						console.log(response.data)
						_this.proallData = response.data.list
						console.log("++++++", response.data.list)
						_this.pageInfo.total = response.data.total
					}).catch(function(error) {
						console.log(error)
					})
			},
			sellorderbillone() {
				// searchCondition
				var _this = this
				var searchForm = Object.assign(this.searchCondition, this.pageParam)
				console.log(searchForm)
				this.axios({
					url: "http://127.0.0.1:8089/eims/sellOrderBill",
					method: "get",
					params: searchForm
				}).then(response => {
					
					this.SellOrderBill = response.data.list[0]
					console.log("hama" + this.SellOrderBill)
					this.SellOrderBill.sellOrderDetails.forEach(bill=>{
						bill.sellPrice1 = bill.sellPrice
						console.log("sellprice1:"+bill.sellPrice1)
					})
					console.log(this.SellOrderBill)
					this.ruleForm.employeeName = this.SellOrderBill.employeeName
					this.ruleForm.employeeId = this.SellOrderBill.employeeId
					this.kehuinput.customerName = this.SellOrderBill.customerName
					this.ruleForm.warehouseName = this.SellOrderBill.warehouseName
					// this.tableData = response.data.list
					// this.tableTotal = response.data.total
						console.log("hama" +this.SellOrderBill)
				}).catch(error => {

				})

			}




		},
		created() {
			this.isAdd = typeof(this.$route.params.purchId) == "undefined"
			this.SellOrderBill.sellOrderDocunum = "dd" + this.getProjectNum() + Math.floor(Math.random() * 10000)

			//this.getDocuNum("CGD")
			/* if(!this.isAdd){
				this.axios({
					url:"http://localhost:8080/eims/purchase/one",
					method:"get",
					params:{"id":this.$route.params.purchId}
				}).then((response=>{
					this.ruleForm = response.data.list
				}).catch(error=>{
					
				})
			}	 */
			this.sellall()
			this.sellallprotype()
			this.sellallpro()
			if (this.$route.params.sellOrderId == undefined || this.$route.params.sellOrderId == "") {
				console.log("666")

			} else {
				this.sellorderbillone()
			}
			console.log("route :" + this.$route.params.sellOrderId)




		}
	}
</script>

<style>
	/* 表头与表行对齐 */
	#Purchase .cell {
		padding-left: 0px;
	}

	/* 加号按钮 */
	#Purchase .el-input-group__append,
	.el-input-group__prepend {
		padding: 0px 18px;
	}

	#Purchase .el-table .el-input-group__append,
	.el-input-group__prepend {
		padding: 0px 15px;
	}

	#Purchase .el-table {
		padding: 0px 10px;
	}

	#Purchase .el-table td,
	#Purchase .el-table th {
		padding: 6px 0px;
	}

	#Purchase .el-footer {
		padding-bottom: 20px;
	}

	#Purchase .el-main {
		padding: 15px;
	}

	.underline-input {
		border: 0px;
		outline: none;
		width: 105px;
		padding: 1px 0px;
		border-bottom: 1px solid rgb(204, 204, 204);
	}

	.underline-input:hover {
		border-bottom: 1px solid rgb(35, 134, 238);
	}

	#Purchase .el-dialog .el-dialog__body {
		padding: 20px;
	}
</style>
