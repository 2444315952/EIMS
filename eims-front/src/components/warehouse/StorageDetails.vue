<template>
	<div id="addStorage">
		<el-container>
			<el-container>
				<el-main>
					<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
						<el-row>
							<el-col :span="9">
								<el-form-item label="单据编号:" prop="warehouseDocunum">
									<el-input v-model="ruleForm.warehouseDocunum" :disabled="true"
										style="float: left; width: 250px;"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="7">
								<el-form-item label="单据日期:" prop="documentDate">
									<el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.documentDate"
										:disabled="isdisabled" style="width: 100%; float: left;"></el-date-picker>
								</el-form-item>
							</el-col>
							<el-col class="line" :span="6"></el-col>
							<el-col class="line" :span="2"></el-col>
						</el-row>
						<el-row>
							<el-col :span="9">
								<el-form-item label="入库类型:" prop="storageType">
									<el-select v-model="ruleForm.storageType" placeholder="请选择" :disabled="isdisabled"
										style="width: 250px;float: left;">
										<el-option label="采购单" value="采购单"></el-option>
										<el-option label="退货售单" value="退货售单"></el-option>
									</el-select>
									<el-button icon="el-icon-thumb" size="large" style="float: left;"
										@click="selectChange()" :disabled="isdisabled">
									</el-button>
								</el-form-item>
							</el-col>
							<el-col :span="7">
								<el-form-item label="入库仓库:" prop="warehouseName">
									<el-input v-model="ruleForm.warehouseName" :disabled="isdisabled"
										style="float: left; width: 100%;"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="9">
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="1"></el-col>
							<el-col :span="22">
								<el-table ref="multipleTable" :data="Product" :height="tableHeight"
									tooltip-effect="dark" style="width: 100%; text-align :center; height:230px;"
									max-height="230" @selection-change="handleSelectionChange">
									<el-table-column label="操作" width="85">
										<template #default="scope">
											<el-button icon="el-icon-remove" size="mini" :disabled="isdisabled"
												@click="deleteRow(scope.$index,Product)" circle>
											</el-button>
										</template>
									</el-table-column>
									<el-table-column label="产品名称" prop="productName" width="130">
									</el-table-column>
									<el-table-column label="产品图片" prop="productPicture" width="120">
										<template #default="scope">
											<el-image :src="scope.row.productPicture"
												style="width: 55px; height: 55px;"></el-image>
											<!-- <img :src="scope.row.productPicture" style="width: 55px; height: 55px;" :fit="fit"/> -->
										</template>
									</el-table-column>
									<el-table-column prop="specModel" label="产品规格" width="120">
									</el-table-column>
									<el-table-column prop="productUnit" label="单位" width="120">
									</el-table-column>
									<el-table-column label="入库数量" prop="inventoryQuantity" width="150"
										show-overflow-tooltip>
										<template #default="scope">
											<el-input v-model="scope.row.inventoryQuantity" :disabled="isdisabled">
											</el-input>
										</template>
									</el-table-column>
									<el-table-column label="备注" width="270" prop="commodityNote" show-overflow-tooltip>
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
							<el-col :span="1"></el-col>
							<el-col :span="9">
								<el-form-item label="业务员:" prop="employeeName">
									<el-input v-model="ruleForm.employeeName" style="float:left; width: 250px;"
										:disabled="isdisabled">
									</el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<br />
						<el-form-item>
							<el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
							<el-button @click="resetForm('ruleForm')">取消</el-button>
						</el-form-item>
					</el-form>
					<el-dialog title="采购单" v-model="SelectPurchase" :width="'800px'">
						<el-row>
							<el-col :span="14"></el-col>
							<el-col :span="10">
								<el-input class="inline-input" placeholder="采购单号/供应商/仓库/审核状态"
									@keyup.enter.native="search" style="width: 280px; padding-bottom: 6px;"
									v-model="searchInput" size="medium">
									<template #append>
										<el-button icon="el-icon-search" size="small" @click="search"></el-button>
									</template>
								</el-input>
							</el-col>
						</el-row>

						<el-row></el-row>
						<el-row></el-row>
						<el-row>
							<el-col :span="1"></el-col>
							<el-col :span="22">
								<el-table :data="Purchase" tooltip-effect="dark"
									style="width:100%; height:230px; text-align: center;" max-height="300"
									@selection-change="handleSelectionChange">
									<el-table-column type="selection" width="55">
									</el-table-column>
									<el-table-column label="采购单号" width="120" prop="purchDocunum">

									</el-table-column>
									<el-table-column label="供应商名" prop="supplierName" width="90" show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="仓库名" prop="warehouseName" width="90" show-overflow-tooltip>
									</el-table-column>
									<el-table-column prop="transactionAmount" label="交易金额" width="90"
										show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="退货状态" width="90" show-overflow-tooltip>
										<template #default="scope">
											<p v-if="Purchase[scope.$index].returnState==0">未退货</p>
											<p v-if="Purchase[scope.$index].returnState==1">部分退货</p>
											<p v-if="Purchase[scope.$index].returnState==2">全部退货</p>
										</template>
									</el-table-column>
									<el-table-column label="审核状态" prop="audited" width="90" show-overflow-tooltip>
										<template #default="scope">
											<p v-if="Purchase[scope.$index].audited==0">未审核</p>
											<p v-if="Purchase[scope.$index].audited==1">已审核</p>
										</template>
									</el-table-column>
									<el-table-column prop="employeeName" label="操作人" width="90" show-overflow-tooltip>
									</el-table-column>
								</el-table>
							</el-col>
							<el-col :span="1"></el-col>
						</el-row>
						<el-row>
							<el-col :span="5"></el-col>
							<el-col :span="14">
								<el-pagination :current-page="queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
									:page-size="queryForm.pageSize" layout=" total, sizes, prev, pager, next, jumper"
									:total="queryForm.total" @size-change="handleSizeChange"
									@current-change="handleCurrentChange" />
							</el-col>
							<el-col :span="5"></el-col>
						</el-row>

						<template #footer>
							<span class="dialog-footer">
								<el-button @click="SelectPurchase = false">取 消</el-button>
								<el-button type="primary" @click="SelectPurchase = false">确定</el-button>
							</span>
						</template>

					</el-dialog>
					<el-dialog title="退货售单" v-model="SelectSales" :width="'800px'">
						<el-row>
							<el-col :span="14"></el-col>
							<el-col :span="10">
								<el-input class="inline-input" placeholder="退货单号/客户名/仓库/审核状态" style="width: 280px;" @keyup.enter.native="ReturnSear"
									v-model="SalesInput" size="medium">
									<template #append>
										<el-button icon="el-icon-search" size="small" @click="ReturnSear"></el-button>
									</template>
								</el-input>
							</el-col>
						</el-row>

						<el-row></el-row>
						<el-row></el-row>
						<el-row>
							<el-col :span="1"></el-col>
							<el-col :span="22">
								<el-table :data="Sales" tooltip-effect="dark"
									style="width:100%; height:230px; text-align: center;" max-height="250"
									@selection-change="handleSelectionChange">
									<el-table-column type="selection" width="55">
									</el-table-column>
									<el-table-column label="退货单号" width="100" prop="sellReturnDocunum">

									</el-table-column>
									<el-table-column label="客户名" prop="customerName" width="90" show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="仓库名" prop="warehouseName" width="90" show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="退款方式" prop="returnPaymentTerm" width="90"
										show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="审核状态" prop="audited" width="80" show-overflow-tooltip>
										<template #default="scope">
											<p v-if="Sales[scope.$index].audited==0">未审核</p>
											<p v-if="Sales[scope.$index].audited==1">已审核</p>
										</template>
									</el-table-column>
									<el-table-column property="employeeName" label="操作人" width="80"
										show-overflow-tooltip>
									</el-table-column>
									<el-table-column property="retSellRemark" label="备注" width="130"
										show-overflow-tooltip>
									</el-table-column>
								</el-table>
							</el-col>
							<el-col :span="1"></el-col>
						</el-row>
						<el-row>
							<el-col :span="5"></el-col>
							<el-col :span="14">
								<el-pagination :current-page="queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
									:page-size="queryForm.pageSize" layout=" total, sizes, prev, pager, next, jumper"
									:total="queryForm.total" @size-change="handleSizeChange"
									@current-change="handleCurrentChange" />
							</el-col>
							<el-col :span="5"></el-col>
						</el-row>
						<template #footer>
							<span class="dialog-footer">
								<el-button @click="SelectSales = false">取 消</el-button>
								<el-button type="primary" @click="SelectSales = false">确定</el-button>
							</span>
						</template>
					</el-dialog>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>


<script>
	import qs from 'qs'
	export default {
		data() {
			return {
				SalesInput: '',
				searchInput: '',
				isdisabled: false,
				Product: [{
					productName: '茶叶',
					productPicture: '图片',
					specModel: '500g',
					inventoryQuantity: '1000g',
					productUnit: 'g',
					commodityNote: '常温保存'
				}],
				Sales: [],
				Purchase: [],
				SelectList: [],
				TypeList: [],
				SelectSales: false,
				SelectPurchase: false,
				ruleForm: {
					storageType: '',
					warehouseDocunum: '',
					productName: '',
					documentDate: '',
					delivery: false,
					warehouseName: '',
					documentsNote: '',
					employeeName: '',
				},
				tableData: [{
					number: '1',
					foldWarehouse: '默认仓',
					SpecModel: '深圳分店',
					Unit: '包',
					name: '王小虎',
					address: '已作废'
				}, {
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
				queryType: 'all',
				rules: {
					warehouseDocunum: [{
							required: true,
							message: '请输入单据编号',
							trigger: 'blur'
						},
						{
							min: 3,
							max: 8,
							message: '长度在 3 到 5 个字符',
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
			handleCurrentChange(val) {
				this.loading = true
				this.queryForm.pageNum = val
				if (this.queryType == 'all')
					this.BuyerList(),
					this.ReturnsList()
				else if (this.queryType == 'returns')
					this.ReturnSear()
				else if (this.queryType == 'search')
					this.search()
			},
			handleSizeChange(val) {
				this.loading = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all')
					this.BuyerList(),
					this.ReturnsList()
				else if (this.queryType == 'returns')
					this.ReturnSear()
				else if (this.queryType == 'search')
					this.search()
			},
			//采购单的搜索框查询
			search() {
				this.queryType = 'search'
				//this.queryForm.audited = this.searchInput
				this.queryForm.purchDocunum = this.searchInput
				this.queryForm.supplierName = this.searchInput
				this.queryForm.warehouseName = this.searchInput
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/purchase/search',
					params: this.queryForm
				}).then(res => {
					this.queryForm.total = res.data.total
					this.Purchase = res.data.list
					console.log(res)
				}).catch(err => {

				})
			},
			//退货售单的搜索框查询
			ReturnSear() {
				this.queryType = 'returns'
				this.queryForm.customerName = this.SalesInput
				this.queryForm.sellReturnDocunum = this.SalesInput
				this.queryForm.warehouseName = this.SalesInput
				//this.queryForm.audited=this.SalesInput
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/sellReturn/search',
					params: this.queryForm
				}).then(res => {
					this.queryForm.total = res.data
					this.Sales = res.data.list
				}).catch(err => {

				})
			},
			//根据选中下拉框的值弹出相应对应框
			selectChange() {
				if (this.ruleForm.storageType == '采购单') {
					this.SelectPurchase = true
					this.BuyerList()
				} else if (this.ruleForm.storageType == '退货售单') {
					this.SelectSales = true
					this.ReturnsList()
				} else {
					this.SelectPurchase = false
					this.SelectSales = false
				}
			},
			//新增入库单
			addStorage() {
				this.axios({
					method: 'post',
					url: 'http://localhost:8089/eims/warehouseWarrant',
				}).then(res => {
					//console.log(res)
					//this.ruleForm=this.data
				})
			},
			//查询采购单表格中的数据
			BuyerList() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/purchase',
					params: {
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize
					}
				}).then(res => {
					//console.log(res)
					this.queryForm.total = res.data.total
					this.Purchase = res.data.list
				}).catch(err => {

				})
			},
			//查询退货售单表格中的数据
			ReturnsList() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/sellReturn',
					params: {
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize
					}
				}).then(res => {
					this.queryForm.total = res.data.total
					this.Sales = res.data.list
					console.log(res)
				})

			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						alert('submit!');
					} else {
						console.log('error submit!!');
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
					this.Product = res.data.warehousingDetailList
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
			console.log(this.$route.params.warehouseWarrantId)
			if (typeof(this.$route.params.warehouseWarrantId) == "undefined") {} else {
				this.show()
			}
		}
	}
</script>
<style>
	#addStorage {
		background-color: aliceblue;
	}

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
		height: 600px;
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

	#addStorage .dialog .el-table {
		padding: 0px 10px;
	}

	#addStorage .el-table td,
	#addStorage .el-table th {
		padding: 6px 0;
	}
</style>
