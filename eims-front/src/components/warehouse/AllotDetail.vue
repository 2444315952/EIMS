<template>
	<div id="addAllot">
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/Allot">调拨单列表</a></el-breadcrumb-item>
			<el-breadcrumb-item>调拨单</el-breadcrumb-item>
		</el-breadcrumb>
		<el-container style="background-color#F9FAFC;padding-top: 15px;">
			<el-main style="background-color:#F9FAFC">
				<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
					<el-row></el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="调拨单号:" prop="transferDocunum">
								<el-input v-model="ruleForm.transferDocunum" :disabled="true"
									style="float: left; width: 250px;"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="调出仓库:" style="float: left;" prop="foldWarehouseName">
								<el-input v-model="ruleForm.foldWarehouseName" style="width: 250px;" size="medium"
									disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="foldwarehouse.dialogVisible = true;foldwarehouseLoadData()">
										</el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="调入仓库:" style="float: left;" prop="exportWarehouseName">
								<el-input v-model="ruleForm.exportWarehouseName" style="width: 250px;" size="medium"
									disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="exportwarehouse.dialogVisible = true;exportwarehouseLoadData()">
										</el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>


					</el-row>
					<el-row>
						<!-- <el-col :span="8">
							<el-form-item label="调出仓库:" prop="foldWarehouseName">
								<el-select v-model="ruleForm.foldWarehouseName" @change="selectFoldWarehouse"
									@click="queryFoldWarehouse()" :disabled="isdisabled" placeholder="请选择"
									style="width: 250px;float: left;">
									<el-option v-for="item in SelecFoltList" :label="item.warehouseName"
										:key="item.value" :value="item.warehouseName"></el-option>
								</el-select>
							</el-form-item>
						</el-col> -->
						<el-col :span="8">
							<el-form-item label="出库日期:" prop="outboundDate">
								<el-date-picker type="datetime" placeholder="选择日期" v-model="ruleForm.outboundDate"
									:disabled="isdisabled" style="width:250px; float: left;"></el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="入库日期:" prop="warehousingDate">
								<el-date-picker type="datetime" placeholder="选择日期" v-model="ruleForm.warehousingDate"
									:disabled="isdisabled" style="width:250px; float: left;"></el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="业务员:" prop="employeeName">
								<el-select v-model="ruleForm.employeeName" @change="selectEmployee"
									:disabled="isdisabled" placeholder="请选择" @click="queryEmployee()"
									style="float:left; width: 250px;">
									<el-option v-for="item in employeeList" :label="item.employeeName"
										:value="item.employeeName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-dialog title="仓库" v-model="foldwarehouse.dialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="foldwarehouse.searchInput" placeholder="请搜索仓库名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" @click="foldwarehouseLoadData()"
												size="mini">
											</el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="foldwarehouse.tableData" max-height="286" style="height: 286px;"
								highlight-current-row @current-change="foldwarehouseSelectionChange">
								<el-table-column property="warehouseName" label="仓库名称"></el-table-column>
								<el-table-column property="employeeName" label="联系人"></el-table-column>
								<el-table-column property="phone" label="联系电话"></el-table-column>
								<el-table-column property="warehouseAddress" label="联系地址"></el-table-column>
							</el-table>
							<el-row>
								<el-col :span="24">
									<el-pagination style="float: right;margin-top: 15px;"
										@size-change="foldwarehouseSizeChange"
										@current-change="foldwarehouseCurrentChange" :page-sizes="[10,20,40,80]"
										:page-size="foldwarehouse.pageParam.pageSize"
										layout="total, sizes, prev, pager, next, jumper"
										:total="foldwarehouse.tableTotal">
									</el-pagination>
								</el-col>
							</el-row>

							<template #footer>
								<span class="dialog-footer">
									<el-button @click="foldwarehouse.dialogVisible = false" size="medium">取 消
									</el-button>
									<el-button type="primary" @click="foldwarehouseConfirmButton" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>

						<el-dialog title="仓库" v-model="exportwarehouse.dialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="exportwarehouse.searchInput" placeholder="请搜索仓库名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" @click="exportwarehouseLoadData()"
												size="mini">
											</el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="exportwarehouse.tableData" max-height="286" style="height: 286px;"
								highlight-current-row @current-change="exportwarehouseSelectionChange">
								<el-table-column property="warehouseName" label="仓库名称"></el-table-column>
								<el-table-column property="employeeName" label="联系人"></el-table-column>
								<el-table-column property="phone" label="联系电话"></el-table-column>
								<el-table-column property="warehouseAddress" label="联系地址"></el-table-column>
							</el-table>
							<el-row>
								<el-col :span="24">
									<el-pagination style="float: right;margin-top: 15px;"
										@size-change="exportwarehouseSizeChange"
										@current-change="exportwarehouseCurrentChange" :page-sizes="[10,20,40,80]"
										:page-size="exportwarehouse.pageParam.pageSize"
										layout="total, sizes, prev, pager, next, jumper"
										:total="exportwarehouse.tableTotal">
									</el-pagination>
								</el-col>
							</el-row>

							<template #footer>
								<span class="dialog-footer">
									<el-button @click="exportwarehouse.dialogVisible = false" size="medium">取 消
									</el-button>
									<el-button type="primary" @click="exportwarehouseConfirmButton" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>

					</el-row>
					<br />
					<el-row>
						<el-col :span="1"></el-col>
						<el-col :span="22">
							<el-table ref="multipleTable" :data="ruleForm.transferDetailsList" :height="tableHeight"
								max-height="230" tooltip-effect="dark"
								style="width: 100%; text-align :center; height: 230px;"
								@selection-change="handleSelectionChange">
								<el-table-column label="操作" width="90">
									<template #default="scope">
										<el-button type="primary" icon="el-icon-plus" size="mini" @click="addRow()"
											circle :disabled="isdisabled">
										</el-button>
										<el-button type="primary" icon="el-icon-minus" size="mini"
											@click="removeRow(scope.$index)" circle :disabled="isdisabled"></el-button>
									</template>
								</el-table-column>
								<el-table-column label="产品名称" width="210">
									<template #default="append">
										<el-input v-model="ruleForm.transferDetailsList[append.$index].productName"
											placeholder="请选择要调拨的产品" :disabled="isdisabled">
											<template #suffix>
												<el-button icon="el-icon-more" style="border: none;" size="small"
													@click="getProduct()" :disabled="isdisabled"></el-button>

											</template>
										</el-input>
									</template>
								</el-table-column>
								<el-table-column label="产品图片" width="140">
									<template #default="scope">
										<img v-if="typeof(this.ruleForm.transferDetailsList[0].productId) != 'undefined'"
											:src="ruleForm.transferDetailsList[scope.$index].productPicture"
											style="width: 40px; height: 40px;" />
									</template>
								</el-table-column>
								<el-table-column prop="specModel" label="规格" width="90">
								</el-table-column>
								<el-table-column prop="productUnit" label="单位" width="80">
								</el-table-column>
								<el-table-column property="inventory" label="可用库存" width="100" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="调拨数量" width="160" prop="outboundQuantity" show-overflow-tooltip>
									<template #default="scope">
										<el-input-number
											v-model="ruleForm.transferDetailsList[scope.$index].outboundQuantity"
											size="small" :min="1" :precision="0">
										</el-input-number>
									</template>
								</el-table-column>
								<el-table-column label="备注" width="280" show-overflow-tooltip>
									<template #default="scope">
										<el-input v-model="ruleForm.transferDetailsList[scope.$index].commodityNote"
											:disabled="isdisabled">
										</el-input>
									</template>
								</el-table-column>
							</el-table>
						</el-col>
						<el-col :span="1"></el-col>
					</el-row>
					<br />
					<br />
					<el-row>
						<el-col :span="8">
							<el-form-item label="单据备注:" prop="documentsNote">
								<el-input type="textarea" v-model="ruleForm.documentsNote" :disabled="isdisabled">
								</el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row></el-row>
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<el-col :span="8"></el-col>
						<el-col :span="5">
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
								<el-button @click="$router.push({name:'Allot'})">取消</el-button>
							</el-form-item>
						</el-col>
						<el-col :span="4"></el-col>
						<el-col :span="7">
							<el-form-item label="驳回原因:" prop="reason" v-if="ruleForm.audited==2">
								<span class="ReasonClass">{{ruleForm.reason}}</span>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>

				<el-dialog title="选择产品" v-model="setProduct" :width="'800px'">
					<el-row>
						<el-col :span="17"></el-col>
						<el-col :span="7">
							<el-input class="inline-input" placeholder="请输入产品名" @keyup.enter.native="clickSearch"
								style="width:180px; padding-bottom: 6px;" v-model="searchProduct" size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" @click="clickSearch"></el-button>
								</template>
							</el-input>
						</el-col>
					</el-row>
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<!-- <el-col :span="1"></el-col>
						<el-col :span="22"> -->
						<el-table :data="inventory" tooltip-effect="dark"
							style="width: 100%; text-align: center; height: 240px;" max-height="240"
							@selection-change="ProductSelectChange">
							<el-table-column type="selection" width="55">
							</el-table-column>
							<el-table-column label="仓库" width="125" property="warehouseName">
							</el-table-column>
							<el-table-column label="产品名称" width="130" property="productName">
							</el-table-column>
							<el-table-column label="市场价" width="120" property="marketPrice">
							</el-table-column>
							<el-table-column label="进价" property="purchasePrice" width="120" show-overflow-tooltip>
							</el-table-column>
							<el-table-column property="inventory" label="库存量" width="120" show-overflow-tooltip>
							</el-table-column>
							<el-table-column property="totalCost" label="总成本" width="120" show-overflow-tooltip>
							</el-table-column>
						</el-table>
						<!-- </el-col>
						<el-col :span="1"></el-col> -->
					</el-row>
					<el-row>
						<el-col :span="5"></el-col>
						<el-col :span="14">
							<el-pagination :current-page="queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
								:page-size="queryForm.pageSize" layout=" total, sizes, prev, pager, next, jumper"
								:total="queryForm.total" @size-change="ProductSizeChange"
								@current-change="ProductCurrentChange" />
						</el-col>
						<el-col :span="5"></el-col>
					</el-row>
					<template #footer>
						<span class="dialog-footer">
							<el-button @click="setProduct = false">取 消</el-button>
							<el-button type="primary" @click="AddProduct">确定</el-button>
						</span>
					</template>
				</el-dialog>
			</el-main>
		</el-container>
	</div>
</template>


<script>
	export default {
		data() {
			return {
				abc: '/img/1.jpg',
				queryForm: {
					pageNum: 1,
					pageSize: 2,
					total: ''
					//workPointId: 1
					//currentPage,pagesize:对应后端的
				},
				searchProduct:'',
				value1: '',
				isAdd: true,
				value2: '',
				isdisabled: false,
				employeeList: [],
				SelecFoltList: [],
				SelectExporList: [],
				SelectList: [],
				searchInput: [],
				inventory: [],
				sourceRowIndex: 0,
				wareRow: null,
				setProduct: false,
				ruleForm: {
					reason:'',
					transferDetailsList: [{}],
					foldWarehouseName: '',
					transferDocunum:'',
					outboundQuantity: '',
					employeeName: '',
					specModel: '',
					productUnit: '',
					commodityNote: '',
					exportWarehouseName: '',
					outboundDate: '',
					warehousingDate: '',
					delivery: false,
					documentsNote: ''
				},
				foldwarehouse: {
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
				exportwarehouse: {
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
				docuNumSequence: '',
				multipleSelection: [],
				queryType: 'all',
				rules: {
					transferDocunum: [{
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
					exportWarehouseName: [{
						required: true,
						message: '请选择调入仓库',
						trigger: 'change'
					}],
					foldWarehouseName: [{
						required: true,
						message: '请选择调出仓库',
						trigger: 'change'
					}],
					warehousingDate: [{
						type: 'date',
						required: true,
						message: '请选择入库日期',
						trigger: 'change'
					}],
					outboundDate: [{
						type: 'date',
						required: true,
						message: '请选择出库日期',
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
			ProductSelectChange(val) {
				this.multipleSelection = val
				console.log(val)
			},

			//生成单据编号，和时间
			getDocuDate(prefix) {
				const nowDate = new Date()
				var month = nowDate.getMonth() + 1

				month = month > 10 ? month : '0' + month
				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date
				this.ruleForm.transferDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"
				this.axios({
					url: 'http://localhost:8089/eims/transfer/search',
					method: 'get',
					params: {
						'transferDocunum': this.ruleForm.transferDocunum
					}
				}).then(res => {
					console.log(res)
					const docuNumSequence = (Array(5).join(0) + ( res.data.list.length + 1)).slice(-5)
					this.ruleForm.transferDocunum = this.ruleForm.transferDocunum + docuNumSequence
				}).catch(err => {

				})
			},

			//选择要调拨的产品
			AddProduct() {
				console.log("选中的值为：")
				console.log(this.multipleSelection)
				if (this.multipleSelection.length > 0) {
					this.setProduct = false
					this.ruleForm.transferDetailsList = this.multipleSelection
				} else {
					this.$message({
						type: 'info',
						message: '请选择要调拨的产品'
					});
				}

			},

			//库存表格页大小
			ProductSizeChange(val) {
				this.loading = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all') {
					this.getProduct()
				} else if (this.queryType == 'search')
					this.clickSearch()
			},

			//库存表当前页
			ProductCurrentChange(val) {
				this.queryForm.pageNum = val
				if (this.queryType == 'all') {
					this.getProduct()
				} else if (this.queryType == 'search')
					this.clickSearch()
			},

			//库存表的搜索框查询
			clickSearch() {
				this.queryType = 'search'
				this.queryForm.productName = this.searchProduct
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/inventory/search',
					params: this.queryForm
				}).then(res => {
					console.log("搜索框查询：")
					console.log(res)
					this.queryForm.total = res.data.total
					this.inventory = res.data.list
				}).catch(err => {

				})
			},

			//查询仓库中产品的库存
			getProduct(index) {
				this.sourceRowIndex = index
				console.log("调出仓库为：")
				console.log(this.ruleForm.foldWarehouseName)
				if (this.ruleForm.foldWarehouseName === '') {
					this.$message({
						type: 'info',
						message: '请选择调出仓库'
					})
					this.setProduct = false
				} else {
					this.setProduct = true
					this.axios({
						method: 'get',
						url: 'http://localhost:8089/eims/inventory/all',
						params: {
							"warehouseId": this.ruleForm.foldWarehouseId
							// "pageNum": this.queryForm.pageNum,
							// "pageSize": this.queryForm.pageSize
						}
					}).then(res => {
						console.log(res)
						this.queryForm.total = res.data.total
						this.inventory = res.data.list

					}).catch(err => {

					})
				}
			},

			//下拉框查询调出仓库的值
			// queryFoldWarehouse() {
			// 	this.axios({
			// 		method: 'get',
			// 		url: 'http://localhost:8089/eims/warehouse',
			// 	}).then(res => {
			// 		console.log(res)
			// 		this.SelecFoltList = res.data.list
			// 	}).catch(err => {

			// 	})
			// },

			//调入仓库下拉框
			selectExportWarehouse(val) {

			},

			//下拉取值的时候绑定调入仓库的值
			selectExportWarehouse() {
				this.SelectExporList.forEach(e => {
					if (e.warehouseName == this.ruleForm.exportWarehouseName) {
						this.ruleForm.exportWarehouseId = e.warehouseId
						return false
					}
				})
			},

			foldwarehouseLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/warehouse/search',
					method: 'get',
					params: Object.assign({
						'warehouseName': this.foldwarehouse.searchInput
					}, this.foldwarehouse.pageParam)
				}).then(response => {
					this.foldwarehouse.tableData = response.data.list
					this.foldwarehouse.tableTotal = response.data.total
				}).catch(error => {

				})
			},
			foldwarehouseSelectionChange(val) {
				this.wareRow = val
				this.foldwarehouse.singleSelection = val
			},
			foldwarehouseSizeChange(val) {
				this.foldwarehouse.pageParam.pageSize = val
				this.foldwarehouseLoadData()
			},
			foldwarehouseCurrentChange(val) {
				this.foldwarehouse.pageParam.pageNum = val
				this.foldwarehouseLoadData()
			},

			foldwarehouseConfirmButton() {
				if (this.wareRow == null) {
					this.$message({
						type: 'info',
						message: '请选择要调拨的仓库'
					});
				} else {
					this.ruleForm.foldWarehouseId = this.foldwarehouse.singleSelection.warehouseId
					this.ruleForm.foldWarehouseName = this.foldwarehouse.singleSelection.warehouseName
					this.foldwarehouse.dialogVisible = false
				}
				this.ruleForm.transferDetailsList = [{}]
			},

			foldwarehouseLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/warehouse/search',
					method: 'get',
					params: Object.assign({
						'warehouseName': this.foldwarehouse.searchInput
					}, this.foldwarehouse.pageParam)
				}).then(response => {
					this.foldwarehouse.tableData = response.data.list
					this.foldwarehouse.tableTotal = response.data.total
				}).catch(error => {

				})
			},


			exportwarehouseSelectionChange(val) {
				this.wareRow = val
				this.exportwarehouse.singleSelection = val
			},
			exportwarehouseSizeChange(val) {
				this.exportwarehouse.pageParam.pageSize = val
				this.exportwarehouseLoadData()
			},
			exportwarehouseCurrentChange(val) {
				this.exportwarehouse.pageParam.pageNum = val
				this.exportwarehouseLoadData()
			},

			exportwarehouseConfirmButton() {
				if (this.wareRow == null) {
					this.$message({
						type: 'info',
						message: '请选择要调拨的仓库'
					});
				} else {
					this.ruleForm.exportWarehouseId = this.exportwarehouse.singleSelection.warehouseId
					this.ruleForm.exportWarehouseName = this.exportwarehouse.singleSelection.warehouseName
					this.exportwarehouse.dialogVisible = false
				}
			},

			exportwarehouseLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/warehouse/search',
					method: 'get',
					params: Object.assign({
						'warehouseName': this.exportwarehouse.searchInput
					}, this.exportwarehouse.pageParam)
				}).then(response => {
					this.exportwarehouse.tableData = response.data.list
					this.exportwarehouse.tableTotal = response.data.total
				}).catch(error => {

				})
			},

			//下拉框查询调入仓库的值
			// queryExportWarehouse() {
			// 	this.axios({
			// 		method: 'get',
			// 		url: 'http://localhost:8089/eims/warehouse',
			// 	}).then(res => {
			// 		console.log(res)
			// 		this.SelectExporList = res.data.list
			// 	}).catch(err => {

			// 	})
			// },

			//下拉框取值的时候绑定调出仓库的id
			// selectFoldWarehouse() {
			// 	this.SelecFoltList.forEach(e => {
			// 		if (e.warehouseName == this.ruleForm.foldWarehouseName) {
			// 			this.ruleForm.foldWarehouseId = e.warehouseId
			// 			return false
			// 		}
			// 	})

			// 	this.ruleForm.transferDetailsList = [{}]
			// },

			//增加行按钮
			addRow() {
				this.ruleForm.transferDetailsList.push({});
			},

			//删除行按钮
			removeRow(index) {
				if (this.ruleForm.transferDetailsList.length > 1)
					this.ruleForm.transferDetailsList.splice(index, 1);
			},

			//点击保存新增调拨单数据
			submitForm(formName) {

				this.ruleForm.transferDetailsList.forEach(detail => {
					detail.transferDocunum = this.ruleForm.transferDocunum
					detail.specModel = detail.specModel
					detail.productUnit = detail.productUnit
				})
				const list = this.ruleForm.transferDetailsList
				for (var i = 0; i < list.length; i++) {
					if (typeof(list[i].productId) == "undefined") {
						this.$message({
							type: 'warning',
							message: '请选择调拨产品'
						})
						return false
					} else if (typeof(list[i].outboundQuantity) == "undefined") {
						this.$message({
							type: 'warning',
							message: '请填写调拨数量'
						})
						return false
					} else if (this.ruleForm.foldWarehouseName == this.ruleForm.exportWarehouseName) {
						this.$message({
							type: 'warning',
							message: '调入仓库和调出仓库不能为同一仓库'
						})
						return false
					} else if (list[i].outboundQuantity > list[i].inventory) {
						this.$message({
							type: 'warning',
							message: '调拨数量超过库存数量，请慎重考虑调拨数量'
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
								url: 'http://localhost:8089/eims/transfer',
								data: this.ruleForm
							}).then(res => {
								//console.log(res)
								this.$message({
									type: 'success',
									message: '调拨单数据新增成功！'
								})
								this.$router.push({
									name: 'Allot'
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
								url: 'http://localhost:8089/eims/transfer',
								data: this.ruleForm
							}).then(res => {
								this.$message({
									type: 'success',
									message: '调拨单信息更改成功！'
								})
								this.$router.push({
									name: 'Allot'
								})
							}).catch(err => {

							})
						}
					} else {
						return false;
					}
				});
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
			//删除表格行
			deleteRow(index, rows) {
				if (rows.length > 1) { //若删除的行是表格的仅存的最后一行,则不能删除
					rows.splice(index, 1);
				}
			},

			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			//查看详情
			show() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/transfer/one',
					params: {
						"id": this.$route.params.transferId
					}
				}).then(res => {
					this.ruleForm = res.data
					this.ruleForm.transferDetailsList = res.data.transferDetailsList
					console.log(this.ruleForm.transferDetailsList[0].productId)
					if (this.ruleForm.audited == 1) {
						this.isdisabled = true
					} else {
						this.isdisabled = false
					}
				})
			},
		},
		created() {
			console.log(this.$route.params.transferId)
			this.ruleForm.transferId = this.$route.params.transferId
			this.isAdd = typeof(this.ruleForm.transferId) == "undefined" || this.ruleForm.transferId == ""
			if (this.isAdd)
				this.getDocuDate("TBD")
			else {
				this.show()
			}
		}
	}
</script>
<style>
	#addAllot {
		/* background-color: aliceblue; */
		/* height: 1200px; */
	}

	#addAllot .el-container {
		height: 100%;
		width: 100%;
	}

	#addAllot .el-header,
	#addAllot .el-footer {
		/* background-color: #B3C0D1; */
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	/* 加号按钮 */
	#addAllot .el-input-group__append,
	.el-input-group__prepend {
		padding: 0px 18px;
	}

	#addAllot .el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}

	#addAllot .el-main {
		background-color: #E9EEF3;
		color: #333;
		padding-left: 5px;
		padding-right: 12px;
		text-align: center;
		height: 100%;

	}
	
	
	#addAllot .ReasonClass{
		font-size: 26px;
		color: #ff0000;
	}


	#addAllot .el-row {
		margin-bottom: 5px;
	}

	#addAllot .el-col {
		border-radius: 4px;
	}

	#addAllot .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

	#addAllot .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	/* .el-input__inner{
			width: 200px;
		} */
	#addAllot .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}

	#addAllot .el-dialog__body {
		padding: 5px;
	}

	#addAllot .el-table td,
	#addAllot .el-table th {
		padding: 6px 0;
	}
</style>
