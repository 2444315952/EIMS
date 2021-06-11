<template>
	<div id="SmartReplenish">

		<el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
			class="demo-ruleForm">

			<el-row>
				<el-col :span="12">
					<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
						<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
						<el-breadcrumb-item><a href="/PurchaseList">采购单列表</a></el-breadcrumb-item>
						<el-breadcrumb-item><a href="/">采购单</a></el-breadcrumb-item>
					</el-breadcrumb>
				</el-col>

				<el-col :span="12">
					<el-button style="float: right;position: relative;bottom:8px;right: 3px;" size="medium"
						type="primary" @click="submitForm('ruleForm')">生成采购单</el-button>
				</el-col>

			</el-row>

			<el-container style="background-color: white;padding-top: 15px;">

				<el-main style="background-color: white;">
					
					<el-row>
						<el-col :span="6">
							<el-form-item label="仓库" style="float: left;" label-width="50px">
								<el-select @click="clickEmployeeSelect()" size="small" @change="changeEmployeeSelect"
									v-model="warehouseSelect" style="width: 150px;">
									<el-option v-for="w in warehouseSelectValue" :label="w.warehouseName"
										:value="w.warehouseId"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						
					</el-row>

					<el-table :data="ruleForm.purchaseDetailList" show-summary max-height="402"
						style="width: 100%;height:402px;">
						<el-table-column label="产品名称" prop="productName">
							<template #default="scope">
								<el-input v-model="ruleForm.purchaseDetailList[scope.$index].productName"
									style="width: 170px" size="small" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="mini"
											@click="productOpenDialog(scope.$index)">
										</el-button>
									</template>
								</el-input>
							</template>
						</el-table-column>
						<el-table-column label="规格型号" prop="specModel">
						</el-table-column>
						<el-table-column label="产品单位" prop="productUnit">
						</el-table-column>
						<el-table-column label="销售在订量" prop="productUnit">
						</el-table-column>
						<el-table-column label="库存余额" prop="productUnit">
						</el-table-column>
						<el-table-column label="采购在订量" prop="productUnit">
						</el-table-column>
						<el-table-column label="最低库存" prop="productUnit">
						</el-table-column>
						<el-table-column label="建议采购量" prop="productUnit">
						</el-table-column>
						<el-table-column label="采购数量" prop="purchaseQuantity">
							<template #default="scope">
								<el-input-number v-model="ruleForm.purchaseDetailList[scope.$index].purchaseQuantity"
									@change="changePriceOrQuantity(scope.$index)" size="small" :min="1" :precision="0">
								</el-input-number>
							</template>
						<el-table-column label="采购价" prop="purchasePrice">
							<template #default="scope">
								<el-input-number v-model="ruleForm.purchaseDetailList[scope.$index].purchasePrice"
									@change="changePriceOrQuantity(scope.$index)" controls-position="right" size="small"
									style="width: 130px;" :precision="2">
								</el-input-number>
							</template>
						</el-table-column>
						</el-table-column>
						<el-table-column label="小计" prop="purchaseSubtotal">
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

						<el-table :data="product.tableData" max-height="286" style="height: 286px;"
							@selection-change="productSelectionChange">
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
				<el-footer style="height: 56;">
				</el-footer>

			</el-container>
		</el-form>

	</div>
</template>

<script>
	export default {
		name: "SmartReplenish",
		data() {
			return {
				warehouseSelect:'',
				warehouseSelectValue: [],
				isAdd: true,
				ruleForm: {
					purchaseDetailList: [{}]
				},
				rules: {
					purchDocunum: [{
						required: true,
						message: '请输入单据编号',
						trigger: 'change'
					}],
					documentDate: [{
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
					supplierName: [{
						required: true,
						message: '请选择供应商',
						trigger: 'change'
					}]
				},

				docuNumSequence: '',
				employeeSelectValue: [],
				supplier: {
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
				this.warehouseLoadData()
				
				/* this.axios({
					url: "http://localhost:8089/eims/purchase/one",
					method: "get",
					params: {
						"id": this.ruleForm.purchId
					}
				}).then(response => {
					this.ruleForm = response.data
				}).catch(error => {
				
				}) */
			},
			warehouseLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/warehouse/search',
					method: 'get'
				}).then(response => {
					this.warehouseSelectValue = response.data.list
					this.warehouseSelect = this.warehouseSelectValue[0].warehouseId
				}).catch(error => {
			
				})
			},
			getDocuNum(prefix) {
				const nowDate = new Date()

				var month = nowDate.getMonth() + 1
				month = month > 10 ? month : '0' + month

				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date

				this.ruleForm.purchDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"

				this.axios({
					url: 'http://localhost:8089/eims/purchase/docuNumCount',
					method: 'get',
					params: {
						'purchDocunum': this.ruleForm.purchDocunum
					}
				}).then(response => {
					console.log(response)
					const docuNumSequence = (Array(5).join(0) + (response.data + 1)).slice(-5)
					this.ruleForm.purchDocunum = this.ruleForm.purchDocunum + docuNumSequence
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
			supplierLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/supplier/search',
					method: 'get',
					params: Object.assign({
						'supplierName': this.supplier.searchInput
					}, this.supplier.pageParam)
				}).then(response => {
					this.supplier.tableData = response.data.list
					this.supplier.tableTotal = response.data.total
				}).catch(error => {

				})
			},
			supplierSelectionChange(val) {
				this.supplier.singleSelection = val
			},
			supplierSizeChange(val) {
				this.supplier.pageParam.pageSize = val
				this.supplierLoadData()
			},
			supplierCurrentChange(val) {
				this.supplier.pageParam.pageNum = val
				this.supplierLoadData()
			},
			supplierConfirmButton() {
				this.supplier.dialogVisible = false
				this.ruleForm.supplierId = this.supplier.singleSelection.supplierId
				this.ruleForm.supplierName = this.supplier.singleSelection.supplierName
			},
			productOpenDialog(index) {
				this.product.sourceRowIndex = index
				this.product.dialogVisible = true
				this.productLoadData()
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

				//属性名 参考成本价替换为采购价
				var newData = JSON.parse(JSON.stringify(this.product.multipleSelection).replace(/referCost/g,
					"purchasePrice"))

				newData.forEach(detail => {
					//设置明细默认的采购量和小计，和采购单据编号
					detail.purchaseQuantity = 1
					detail.purchaseSubtotal = detail.purchasePrice
					detail.purchDocunum = this.ruleForm.purchDocunum

					this.ruleForm.purchaseDetailList[this.product.sourceRowIndex] = detail
					this.product.sourceRowIndex++

				})

				//计算交易金额
				this.calcTransactionAmount();

			},
			addRow() {
				this.ruleForm.purchaseDetailList.push({});
			},
			removeRow(index) {
				if (this.ruleForm.purchaseDetailList.length > 1)
					this.ruleForm.purchaseDetailList.splice(index, 1);
			},
			changePriceOrQuantity(index) {
				const detail = this.ruleForm.purchaseDetailList[index]
				const subtotal = detail.purchasePrice * detail.purchaseQuantity
				this.ruleForm.purchaseDetailList[index].purchaseSubtotal = subtotal

				//计算交易金额
				this.calcTransactionAmount()
			},
			calcTransactionAmount() {
				var transactionAmount = 0
				this.ruleForm.purchaseDetailList.forEach(detail => {
					transactionAmount += detail.purchaseSubtotal
				})
				this.ruleForm.transactionAmount = transactionAmount
			},
			submitForm(formName) {
				var detailValidate = true

				const list = this.ruleForm.purchaseDetailList
				for(var i=0;i<list.length;i++){
					if (typeof(list[i].productId) == "undefined" || list[i].purchasePrice == "") {
						this.$message({
							type: 'warning',
							message: '请选择采购产品'
						})
						detailValidate = false
						return false
					} else if (typeof(list[i].purchasePrice) == "undefined" || list[i].purchasePrice == "") {
						this.$message({
							type: 'warning',
							message: '请填写采购价'
						})
						detailValidate = false
						return false
					} else if (typeof(list[i].purchaseQuantity) == "undefined" || list[i].purchaseQuantity == "") {
						this.$message({
							type: 'warning',
							message: '请填写采购数量'
						})
						detailValidate = false
						return false
					}
				}

				if (!detailValidate)
					return false

				this.$refs[formName].validate((valid) => {
					if (valid) {

						if (this.isAdd) {
							this.axios({
								url: 'http://localhost:8089/eims/purchase',
								method: 'post',
								data: this.ruleForm
							}).then(response => {
								this.$message({
									type: 'success',
									message: '采购单数据新增成功！'
								})

								this.$router.push({
									name: 'PurchaseList'
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
							}else if(this.ruleForm.inStorage == 1){
								this.$message({
									type:'info',
									message:'已入库的数据无法更改'
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
								url:'http://localhost:8089/eims/purchase',
								method:'put',
								data:this.ruleForm
							}).then(response=>{
								this.$message({
									type:'success',
									message:'采购单信息更改成功！'
								})
								
								this.$router.push({
									name:'PurchaseList'
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
			this.loadData()
		}
	}
</script>

<style>
	/* 表头与表行对齐 */
	#SmartReplenish .cell {
		padding-left: 0px;
	}

	/* 加号按钮 */
	#SmartReplenish .el-input-group__append,
	.el-input-group__prepend {
		padding: 0px 18px;
	}

	#SmartReplenish .el-table .el-input-group__append,
	.el-input-group__prepend {
		padding: 0px 15px;
	}

	#SmartReplenish .el-table {
		padding: 0px 10px;
	}

	#SmartReplenish .el-table td,
	#SmartReplenish .el-table th {
		padding: 6px 0px;
	}

	#SmartReplenish .el-dialog .el-table td,
	#SmartReplenish .el-dialog .el-table th {
		padding: 9px 0px;
	}

	#SmartReplenish .el-footer {
		padding-bottom: 20px;
	}

	#SmartReplenish .el-main {
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

	#SmartReplenish .el-dialog .el-dialog__body {
		padding: 20px;
	}
</style>
