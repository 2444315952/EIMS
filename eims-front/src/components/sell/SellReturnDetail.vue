<template>
	<div id="Purchase">

		<el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
			class="demo-ruleForm">

			<el-row>
				<el-col :span="12">
					<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
						<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
						<el-breadcrumb-item><a href="/sellbill">销售退货单列表</a></el-breadcrumb-item>
						<el-breadcrumb-item><a href="/">销售退货单</a></el-breadcrumb-item>
						
					</el-breadcrumb>
				</el-col>

				<el-col :span="12">
					<el-button style="float: right;position: relative;bottom:8px;right: 3px;" size="medium"
						type="primary" @click="submitForm('ruleForm')">保存</el-button>
				</el-col>

			</el-row>

			<el-container style="background-color: white;padding-top: 15px;">

				<el-main style="background-color: white;">
					<el-row>
						<el-col :span="8">
							<el-form-item label="单据编号" style="float: left;" prop="sellReturnDocunum">
								<el-input v-model="ruleForm.sellReturnDocunum" size="medium" disabled></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="8">
							<el-form-item label="退货日期" style="float: left;" prop="sellReturnHirthday" required>
								<el-date-picker type="datetime" placeholder="选择日期" size="medium"
									v-model="ruleForm.sellReturnHirthday" style="width: 206px;"></el-date-picker>
							</el-form-item>
						</el-col>

						<el-col :span="8">
							<el-form-item label="业务员" style="float: left;" prop="employeeId" required>
								<el-select @click="clickEmployeeSelect()" @change="changeEmployeeSelect"
									v-model="ruleForm.employeeName" style="width: 206px;" placeholder="请选择业务员">
									<el-option v-for="e in employeeSelectValue" :label="e.employeeName"
										:value="e.employeeName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="销售单" style="float: left;" prop="sellDocunum" required>
								<el-input v-model="ruleForm.sellDocunum" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="sellBill.dialogVisible = true;sellBillLoadData()">
										</el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>
					
						<el-dialog title="销售单" v-model="sellBill.dialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="sellBill.searchInput" placeholder="请搜索销售单编号. " size="small">
										<template #append>
											<el-button @click="sellBillLoadData()" icon="el-icon-search" size="mini">
											</el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="sellBill.tableData" max-height="286" style="height: 286px;"
								highlight-current-row @current-change="sellBillSelectionChange" >
								<el-table-column property="sellDocunum" label="销售单编号"></el-table-column>
								<el-table-column property="sellHirthday" label="单据日期 "></el-table-column>
								<el-table-column property="customerName" label="客户"></el-table-column>
								<el-table-column property="employeeName" label="业务员"></el-table-column>
								<el-table-column property="warehouseName" label="出库仓库"></el-table-column>
								<!-- <el-table-column property="sellAddress" label="地址"></el-table-column> -->
								<el-table-column property="billPaidAmount" label="成交金额"></el-table-column>
							</el-table>
							
							<el-row>
								<el-col :span="24">
									<el-pagination style="float: right;margin-top: 15px;"
										@size-change="sellBillSizeChange" @current-change="sellBillCurrentChange"
										:page-sizes="[10,20,40,80]" :page-size="sellBill.pageParam.pageSize"
										layout="total, sizes, prev, pager, next, jumper" :total="sellBill.tableTotal">
									</el-pagination>
								</el-col>
							</el-row>

							<template #footer>
								<span class="dialog-footer">
									<el-button @click="sellBill.dialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="sellBillConfirmButton" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>

						<el-col :span="8">
							<el-form-item label="客户" style="float: left;" prop="customerName">
								<el-input v-model="ruleForm.customerName" size="medium" disabled>
									
								</el-input>
							</el-form-item>
						</el-col>

						
						
						<el-col :span="8">
							<el-form-item label="仓库" style="float: left;" prop="warehouseName">
								<el-input v-model="ruleForm.warehouseName" size="medium" disabled>
									
								</el-input>
							</el-form-item>
						</el-col>

					</el-row>

					<el-table :data="ruleForm.sellDetails" show-summary max-height="402"
						style="width: 100%;height:402px;">
						<el-table-column label="产品名称" prop="productName">
							<template #default="scope">
								<el-input v-model="ruleForm.sellDetails[scope.$index].productName"
									style="width: 170px" size="small" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="mini"
											@click="productOpenDialog(scope.$index)">
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
								<el-input-number v-model="ruleForm.sellDetails[scope.$index].sellPrice"
									@change="changePriceOrQuantity(scope.$index)" controls-position="right" size="small"
									style="width: 130px;" :precision="2">
								</el-input-number>
							</template>
						</el-table-column>
						<el-table-column label="采购数量" prop="sellQuantity">
							<template #default="scope">
								<el-input-number v-model="ruleForm.sellDetails[scope.$index].sellQuantity"
									@change="changePriceOrQuantity(scope.$index)" size="small" :min="1" :precision="0">
								</el-input-number>
							</template>
						</el-table-column>
						<el-table-column label="小计" prop="detailPaidAmount">
						</el-table-column>
						
						<el-table-column label="优惠" prop="detailDiscounts">
						</el-table-column>
						<el-table-column label="操作" width="100">
							<template #default="scope">
								<el-button type="primary" icon="el-icon-plus" size="mini" @click="addRow()" circle>
								</el-button>
								<el-button type="primary" icon="el-icon-minus" size="mini"
									@click="removeRow(scope.$index)" circle></el-button>
							</template>
						</el-table-column>
					</el-table>

					<el-dialog title="产品" v-model="product.dialogVisible">
						<el-row type="flex" justify="end" style="padding-bottom: 12px;">
							<el-col :span="7.5">
								<el-input v-model="product.searchInput" placeholder="请搜索产品名称" size="small">
									<template #append>
										<el-button @click="productLoadData" icon="el-icon-search" size="mini">
										</el-button>
									</template>
								</el-input>
							</el-col>

						</el-row>

						<el-table :data="product.tableData " max-height="286" style="height: 286px;"
							@selection-change="productSelectionChange" >
							<el-table-column type="selection" width="25">
							</el-table-column>
							<el-table-column property="productName" label="产品名称"></el-table-column>
							<el-table-column property="specModel" label="规格型号"></el-table-column>
							<el-table-column property="productUnit" label="产品单位"></el-table-column>
							<el-table-column property="referCost" label="参考成本价"></el-table-column>
							<el-table-column property="marketPrice" label="市场价"></el-table-column>
						</el-table>
						<el-row>
							<el-col :span="24">
								<el-pagination style="float: right;margin-top: 15px;" @size-change="productSizeChange"
									@current-change="productCurrentChange" :page-sizes="[10,20,40,80]"
									:page-size="product.pageParam.pageSize"
									layout="total, sizes, prev, pager, next, jumper" :total="product.tableTotal">
								</el-pagination>
							</el-col>
						</el-row>

						<template #footer>
							<span class="dialog-footer">
								<el-button @click="product.dialogVisible = false" size="medium">取 消</el-button>
								<el-button type="primary" @click="productConfirmButton" size="medium">确 定
								</el-button>
							</span>
						</template>
					</el-dialog>
				</el-main>
			
					<el-footer style="height: 106px;">
					
									<el-row style="margin-top: 10px;">
										<el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="ruleForm.sellRemark">
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
												<h1>优惠金额 <el-input v-model="ruleForm.sellDiscounts" disabled></el-input>
												</h1>
											</div>
										</el-col>
										<el-col :span="4">
											<div class="grid-content bg-purple-light">
												<h1>金额<el-input v-model="ruleForm.billPaidAmount" disabled></el-input>
												</h1>
											</div>
										</el-col>
									</el-row>
					
					
					
					
					
									<el-row>
					
					
									
					
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
					sellDetails: [{}]
				},
				rules: {
					sellReturnDocunum: [{
						required: true,
						message: '请输入单据编号',
						trigger: 'change'
					}],
					sellReturnHirthday: [{
						type: 'date',
						required: true,
						message: '请选择单据日期',
						trigger: 'blur'
					}],
					employeeId: [{
						required: true,
						message: '请选择业务员',
						trigger: 'blur'
					}],
					customerName: [{
						required: true,
						message: '请选客户',
						trigger: 'change'
					}]
				},

				docuNumSequence: '',
				employeeSelectValue: [],
				sellBill: {
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
				
				product: {
					dialogVisible: false,
					searchInput: '',
					tableData: [],
					tableTotal: '',
					multipleSelection: [],
					pageParam: {
						"pageNum": 1,
						"pageSize": 10
					},
					sourceRowIndex: 0
				}
			}
		},
		
		methods: {
			loadData(){
				this.axios({
					url: "http://localhost:8089/eims/sellBill/one",
					method: "get",
					params: {
						"id": this.ruleForm.sellId
					}
				}).then(response => {
					this.ruleForm = response.data
					
					console.log("初始化的数据为")
					console.log(this.ruleForm)
					console.log(this.ruleForm.sellDetails)
				}).catch(error => {
				
				})
			},
			getDocuNum(prefix) {
				const nowDate = new Date()
				

				var month = nowDate.getMonth() + 1
				month = month > 10 ? month : '0' + month

				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date

				this.ruleForm.sellReturnDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"

				this.axios({
					url: 'http://localhost:8089/eims/sellReturn/search',
					method: 'get',
					params: {
						'sellReturnDocunum': this.ruleForm.sellReturnDocunum
					}
				}).then(response => {
					console.log(response)
					const sellReturnDocunum = (Array(5).join(0) + (response.data.list.length + 1)).slice(-5)
					this.ruleForm.sellReturnDocunum = this.ruleForm.sellReturnDocunum + sellReturnDocunum
					console.log(sellReturnDocunum)
				}).catch(error => {

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
			changeEmployeeSelect() {
				this.employeeSelectValue.forEach(e => {
					if (e.employeeName == this.ruleForm.employeeName)
						this.ruleForm.employeeId = e.employeeId
				})
			},
			sellBillLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/sellBill/screen',
					method: 'get',
					params: Object.assign({
						// 控制显示单据是否能退货
						'returnState': 0,
						'outStorage': 1
						
					}, this.sellBill.pageParam)
				}).then(response => {
					console.log(response.data.list)
					this.sellBill.tableData = response.data.list
					this.sellBill.tableTotal = response.data.total
					
				}).catch(error => {

				})
			},
			sellBillSelectionChange(val) {
				console.log(val)
				this.sellBill.singleSelection = val
			},
			sellBillSizeChange(val) {
				this.sellBill.pageParam.pageSize = val
				this.sellBillLoadData()
			},
			sellBillCurrentChange(val) {
				this.sellBill.pageParam.pageNum = val
				this.sellBillLoadData()
			},
			sellBillConfirmButton() {
				this.sellBill.dialogVisible = false
				this.ruleForm=this.sellBill.singleSelection
				console.log(this.sellBill.singleSelection)
				
				this.axios({
					url: 'http://localhost:8089/eims/sellDetail/search',
					method: 'get',
					params: Object.assign({
						'sellId': this.ruleForm.sellId
					}, this.sellBill.pageParam)
				}).then(response => {
					this.ruleForm.sellDetails = response.data.list
					this.getDocuNum("SRT")
					console.log(response.data.list)
					
					
				}).catch(error => {
				
				})
				
				
				// this.ruleForm.companyId = this.supplier.singleSelection.companyId
				// this.ruleForm.workPointId = this.supplier.singleSelection.companyId
				// this.ruleForm.customerId = this.supplier.singleSelection.customerId
				// this.ruleForm.customerName = this.supplier.singleSelection.customerName
				// this.ruleForm.paymentTerm = "不知道"
				// this.ruleForm.sellAddress= this.supplier.singleSelection.contact + " " + this.supplier.singleSelection.contactNumber + " " + this.supplier.singleSelection
				// 	.contactAddress + " "
				// 	console.log(this.ruleForm.sellAddress)
			},
			productOpenDialog(index) {
				this.product.sourceRowIndex = index
				this.product.dialogVisible = true
				this.productLoadData()
				console.log(this.ruleForm.sellDetails)
			},
			productLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/product/search',
					method: 'get',
					params: Object.assign({
						'productName': this.product.searchInput
					}, this.product.pageParam)
				}).then(response => {
					this.product.tableData = response.data.list
					this.product.tableTotal = response.data.total
				}).catch(error => {

				})
			},
			productSelectionChange(val) {
				this.product.multipleSelection = val
				console.log("选中产品后:")
				console.log(this.ruleForm.sellDetails)
			},
			productSizeChange(val) {
				this.product.pageParam.pageSize = val
				this.productLoadData()
			},
			productCurrentChange(val) {
				this.product.pageParam.pageNum = val
				this.productLoadData()
			},
			productConfirmButton() {
				this.product.dialogVisible = false
				
				console.log("点击确定按钮后")
				console.log(this.ruleForm.sellDetails)

				this.product.multipleSelection.forEach(detail => {
					console.log()
					
					//设置明细默认的采购量和小计，和采购单据编号
					detail.sellQuantity = 1
					detail.detailPaidAmount = detail.marketPrice
					detail.sellReturnDocunum = this.ruleForm.sellReturnDocunum
					detail.sellPrice = detail.marketPrice
					detail.detailPaidAmount=detail.marketPrice
					detail.detailPayAmount=detail.marketPrice
					detail.detailDiscounts=0
					detail.productModel=detail.specModel
					detail.sellId=this.ruleForm.sellId
					console.log("marketPrice"+detail.marketPrice)
					this.ruleForm.sellDetails[this.product.sourceRowIndex] = detail
						
					this.product.sourceRowIndex++

				})
				
				
				// console.log("循环后的值")
				// console.log(this.ruleForm.sellDetails)
				// this.ruleForm.sellDetails.forEach(bill=>{
				// 	bill.sellPrice = bill.marketPrice
				// 	bill.detailPayAmount=bill.marketPrice
				// 	bill.detailPaidAmount=bill.marketPrice
				// 	bill.detailDiscounts=0
				// 	bill.productModel=bill.specModel
				// 	console.log(bill.productModel)
				// 	console.log("sellprice:"+bill.sellPrice)
				// })
			

				//计算交易金额
				this.calcTransactionAmount();

			},
			
			
			addRow() {
				this.ruleForm.sellDetails.push({});
			},
			removeRow(index) {
				if (this.ruleForm.sellDetails.length > 1)
					this.ruleForm.sellDetails.splice(index, 1);
					this.productConfirmButton()					
			},
			
			changePriceOrQuantity(index) {
				const detail = this.ruleForm.sellDetails[index]
				const subtotal = detail.sellPrice * detail.sellQuantity
				// const subtotal1 = detail.marketPrice1 * detail.sellQuantity
				//计算交易金额
				this.ruleForm.sellDetails[index].detailPaidAmount = subtotal
				//计算应付
				this.ruleForm.sellDetails[index].detailPayAmount= detail.marketPrice * detail.sellQuantity
				//计算优惠金额
				this.ruleForm.sellDetails[index].detailDiscounts= detail.marketPrice * detail.sellQuantity-subtotal
				
			
				
				this.calcTransactionAmount()
			},
			calcTransactionAmount() {
				var transactionAmount = 0
				var transactionAmount1 = 0
				var transactionAmount2 = 0
				this.ruleForm.sellDetails.forEach(detail => {
					transactionAmount += detail.detailPaidAmount
					transactionAmount1 += detail.detailPayAmount
					transactionAmount2 += detail.detailDiscounts
				})
				this.ruleForm.transactionAmount = transactionAmount
				this.ruleForm.billPaidAmount=transactionAmount
				this.ruleForm.billPayAmount=transactionAmount1
				this.ruleForm.sellDiscounts=transactionAmount2
				console.log("金额是:" +transactionAmount)
				console.log("原价是:" +transactionAmount1)
				console.log("优惠是:" +transactionAmount2)
			},
			submitForm(formName) {

				const list = this.ruleForm.sellDetails
				for(var i=0;i<list.length;i++){
					if (typeof(list[i].productId) == "undefined" || list[i].marketPrice == "") {
						this.$message({
							type: 'warning',
							message: '请选择采购产品'
						})
						return false
					} else if (typeof(list[i].marketPrice) == "undefined" || list[i].marketPrice == "") {
						this.$message({
							type: 'warning',
							message: '请填写采购价'
						})
						return false
					} else if (typeof(list[i].sellQuantity) == "undefined" || list[i].sellQuantity == "") {
						this.$message({
							type: 'warning',
							message: '请填写采购数量'
						})
						return false
					}
				}
				this.ruleForm.returnBillsProductList=this.ruleForm.sellDetails
				this.ruleForm.retPayAmount=this.ruleForm.billPayAmount
				this.ruleForm.retPaidAmount=this.ruleForm.billPaidAmount
				this.ruleForm.returnBillsProductList.forEach(m => {
					
					m.retBillPayAmount=m.detailPayAmount
					m.retBillPaidAmount=m.detailPaidAmount
				})
				console.log(this.ruleForm.returnBillsProductList)
				// this.ruleForm.ReturnBillsProduct.retBillPayAmount=this.ruleForm.sellDetails.billPayAmount
				// this.ruleForm.ReturnBillsProduct.retBillPaidAmount=this.ruleForm.sellDetails.billPaidAmount
				this.$refs[formName].validate((valid) => {
					if (valid) {

						if (this.isAdd) {
							this.axios({
								url: 'http://localhost:8089/eims/sellReturn',
								method: 'post',
								data: this.ruleForm
							}).then(response => {
								this.$message({
									type: 'success',
									message: '采购退货单数据新增成功！'
								})

								this.$router.push({
									name: 'sellreturn'
								})
							}).catch(error => {

							})
						} else {
						
							if(this.ruleForm.audited == 1){
								this.$message({
									type:'info',
									message:'已审核的数据无法更改'
								})
								this.loadData()
								return false
							}else if(this.ruleForm.outStorage == 1){
								this.$message({
									type:'info',
									message:'已出库的数据无法更改'
								})
								this.loadData()
								return false
							}else if(this.ruleForm.paymentStatus == 1 || this.ruleForm.paymentStatus == 2){
								this.$message({
									type:'info',
									message:'已付款的数据无法更改'
								})
								this.loadData()
								return false
							}else if(this.ruleForm.returnState == 1 || this.ruleForm.returnState == 2){
								this.$message({
									type:'info',
									message:'已退货的数据无法更改'
								})
								this.loadData()
								return false
							}
						
							this.axios({
								url:'http://localhost:8089/eims/sellBill/detail',
								method:'put',
								data:this.ruleForm
							}).then(response=>{
								this.$message({
									type:'success',
									message:'采购单信息更改成功！'
								})
								
								this.$router.push({
									name:'sellbill'
								})
							}).catch(error=>{
								
							})

						}

					} else {
						return false;
					}
				});
			}
		},
		created() {
			this.ruleForm.sellId = this.$route.params.sellId
			this.isAdd = typeof(this.ruleForm.sellId) == "undefined" || this.ruleForm.sellId == ""
			console.log(this.ruleForm.sellId)
			if (this.isAdd)
			
				this.getDocuNum("SRT")
			else 
			
				this.loadData()
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

	#Purchase .el-dialog .el-table td,
	#Purchase .el-dialog .el-table th {
		padding: 9px 0px;
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
