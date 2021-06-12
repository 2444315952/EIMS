<template>
	<div id="Purchase">

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
						type="primary" @click="submitForm('ruleForm')">保存</el-button>
				</el-col>

			</el-row>

			<el-container style="background-color: white;padding-top: 15px;">

				<el-main style="background-color: white;">
					<el-row>
						<el-col :span="8">
							<el-form-item label="单据编号" style="float: left;" prop="purchDocunum">
								<el-input v-model="ruleForm.purchDocunum" size="medium" disabled></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="8">
							<el-form-item label="单据日期" style="float: left;" prop="documentDate" required>
								<el-date-picker type="datetime" placeholder="选择日期" size="medium"
									v-model="ruleForm.documentDate" style="width: 206px;"></el-date-picker>
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
							<el-form-item label="供应商" style="float: left;" prop="supplierName" required>
								<el-input v-model="ruleForm.supplierName" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="supplier.dialogVisible = true;supplierLoadData()">
										</el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>

						<el-dialog title="供应商" v-model="supplier.dialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="supplier.searchInput" placeholder="请搜索供应商名称" size="small">
										<template #append>
											<el-button @click="supplierLoadData()" icon="el-icon-search" size="mini">
											</el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="supplier.tableData" max-height="286" style="height: 286px;"
								highlight-current-row @current-change="supplierSelectionChange">
								<el-table-column property="supplierName" label="供应商名称"></el-table-column>
								<el-table-column property="contact" label="联系人"></el-table-column>
								<el-table-column property="contactNumber" label="联系电话"></el-table-column>
								<el-table-column property="contactAddress" label="联系地址"></el-table-column>
							</el-table>
							<el-row>
								<el-col :span="24">
									<el-pagination style="float: right;margin-top: 15px;"
										@size-change="supplierSizeChange" @current-change="supplierCurrentChange"
										:page-sizes="[10,20,40,80]" :page-size="supplier.pageParam.pageSize"
										layout="total, sizes, prev, pager, next, jumper" :total="supplier.tableTotal">
									</el-pagination>
								</el-col>
							</el-row>

							<template #footer>
								<span class="dialog-footer">
									<el-button @click="supplier.dialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="supplierConfirmButton" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>

						<el-col :span="8">
							<el-form-item label="入库仓库" style="float: left;" prop="warehouseName">
								<el-input v-model="ruleForm.warehouseName" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="warehouse.dialogVisible = true;warehouseLoadData()"></el-button>
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
						<el-table-column label="采购价" prop="purchasePrice">
							<template #default="scope">
								<el-input-number v-model="ruleForm.purchaseDetailList[scope.$index].purchasePrice"
									@change="changePriceOrQuantity(scope.$index)" controls-position="right" size="small"
									style="width: 130px;" :precision="2">
								</el-input-number>
							</template>
						</el-table-column>
						<el-table-column label="采购数量" prop="purchaseQuantity">
							<template #default="scope">
								<el-input-number v-model="ruleForm.purchaseDetailList[scope.$index].purchaseQuantity"
									@change="changePriceOrQuantity(scope.$index)" size="small" :min="1" :precision="0">
								</el-input-number>
							</template>
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
		name: "Purchase",
		data() {
			return {
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
					url: "http://localhost:8089/eims/purchase/one",
					method: "get",
					params: {
						"id": this.ruleForm.purchId
					}
				}).then(response => {
					this.ruleForm = response.data
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
					url: 'http://localhost:8089/eims/purchase/search',
					method: 'get',
					params: {
						'purchDocunum': this.ruleForm.purchDocunum
					}
				}).then(response => {
					console.log(response)
					const docuNumSequence = (Array(5).join(0) + (response.data.list.length + 1)).slice(-5)
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
				this.warehouse.dialogVisible = false
				this.ruleForm.warehouseId = this.warehouse.singleSelection.warehouseId
				this.ruleForm.warehouseName = this.warehouse.singleSelection.warehouseName
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

				const list = this.ruleForm.purchaseDetailList
				for(var i=0;i<list.length;i++){
					if (typeof(list[i].productId) == "undefined" || list[i].purchasePrice == "") {
						this.$message({
							type: 'warning',
							message: '请选择采购产品'
						})
						return false
					} else if (typeof(list[i].purchasePrice) == "undefined" || list[i].purchasePrice == "") {
						this.$message({
							type: 'warning',
							message: '请填写采购价'
						})
						return false
					} else if (typeof(list[i].purchaseQuantity) == "undefined" || list[i].purchaseQuantity == "") {
						this.$message({
							type: 'warning',
							message: '请填写采购数量'
						})
						return false
					}
				}

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
								url:'http://localhost:8089/eims/purchase/detail',
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
			this.ruleForm.purchId = this.$route.params.purchId
			this.isAdd = typeof(this.ruleForm.purchId) == "undefined" || this.ruleForm.purchId == ""

			if (this.isAdd)
				this.getDocuNum("CGD")
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
