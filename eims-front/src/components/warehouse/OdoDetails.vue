<template>
	<div id="addOdo">
		<el-container>
			<el-container>
				<el-main>
					<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
						<el-row>
							<el-col :span="9">
								<el-form-item label="单据编号:" prop="stockOutDocunum">
									<el-input v-model="ruleForm.stockOutDocunum" :disabled="true"
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
								<el-form-item label="出库仓库:" prop="warehouseName">
									<el-select v-model="ruleForm.warehouseName" @change="selectChange"
										:disabled="isdisabled" placeholder="请选择"
										style="width: 250px;float: left;">
										<el-option v-for="item in SelectList" :key="item.stockOutId"
											:label="item.warehouseName" :value="item.stockOutId"></el-option>
									</el-select>
								</el-form-item>
							</el-col>
							<el-col :span="9">
								<el-form-item label="出库类型:" prop="outboundType">
									<el-select v-model="ruleForm.outboundType" placeholder="请选择" :disabled="isdisabled" @click="queryWarehouse()"
										style="width: 250px;float: left;">
										<el-option label="采购入库" value="shanghai"></el-option>
										<el-option label="退货售单" value="beijing"></el-option>
									</el-select>
									<el-button icon="el-icon-thumb" size="large" style="float: left;"
										@click="select=true" :disabled="isdisabled">
									</el-button>
								</el-form-item>
							</el-col>
							<el-col :span="6">
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
									<el-table-column label="产品图片" width="120">
										<template #default="scope">
											<img :src="scope.row.productPicture" style="width: 55px; height: 55px;"
												class="productPicture" />
										</template>
									</el-table-column>
									<el-table-column prop="specModel" label="产品规格" width="120">
									</el-table-column>
									<el-table-column prop="productUnit" label="单位" width="120">
									</el-table-column>
									<el-table-column label="出库数量" prop="deliveryQuantity" width="150"
										show-overflow-tooltip>
										<template #default="scope">
											<el-input v-model="scope.row.deliveryQuantity" :disabled="isdisabled">
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
					<el-dialog title="采购单" v-model="select" :width="'730px'">
						<el-input class="inline-input" placeholder="请输入单据编号或备注" style="width: 240px;" v-model="project"
							size="medium">
							<template #append>
								<el-button icon="el-icon-search" size="small" @click="sel"></el-button>
							</template>
						</el-input>
						<el-row></el-row>
						<el-row></el-row>
						<el-row>
							<el-table :data="Purchase" tooltip-effect="dark" style="width: 100%; text-align: center;"
								max-height="500" @selection-change="handleSelectionChange">
								<el-table-column type="selection" width="55">
								</el-table-column>
								<el-table-column label="采购单据编号" width="160" property="ProductName">

								</el-table-column>
								<el-table-column label="供应商名" width="100" property="SpecModel">
								</el-table-column>
								<el-table-column label="交易金额" property="ProductNumber" width="100"
									show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="付款状态" property="ProductNumber" width="100"
									show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="审核状态" property="ProductNumber" width="100"
									show-overflow-tooltip>
								</el-table-column>
								<el-table-column property="Unit" label="采购员" width="100" show-overflow-tooltip>
								</el-table-column>
							</el-table>
						</el-row>
						<template #footer>
							<span class="dialog-footer">
								<el-button @click="select = false">取 消</el-button>
								<el-button type="primary" @click="select = false">确定</el-button>
							</span>
						</template>
					</el-dialog>
					<el-dialog title="退货售单" v-model="select" :width="'730px'">
						<el-input class="inline-input" placeholder="请输入退单编号" style="width: 240px;" v-model="project"
							size="medium">
							<template #append>
								<el-button icon="el-icon-search" size="small" @click="sel"></el-button>
							</template>
						</el-input>
						<el-row></el-row>
						<el-row></el-row>
						<el-row>
							<el-col :span="1"></el-col>
							<el-col :span="22">
								<el-table :data="Receipt" tooltip-effect="dark" style="width: 100%; text-align: center;"
									max-height="500" @selection-change="handleSelectionChange">
									<el-table-column type="selection" width="55">
									</el-table-column>
									<el-table-column label="收款单据编号" width="180" property="GatherDocunum">
									</el-table-column>
									<el-table-column label="客户名" width="120" property="CustomerName">
									</el-table-column>
									<el-table-column label="退款金额" property="GatherAmount" width="100"
										show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="审核状态" width="100" property="audited">
									</el-table-column>
									<el-table-column property="EmployeeName" label="操作人" width="100"
										show-overflow-tooltip>
									</el-table-column>
								</el-table>
							</el-col>
							<el-col :span="1"></el-col>
						</el-row>
						<template #footer>
							<span class="dialog-footer">
								<el-button @click="select = false">取 消</el-button>
								<el-button type="primary" @click="select = false">确定</el-button>
							</span>
						</template>
					</el-dialog>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>


<script>
	export default {
		data() {
			return {
				isdisabled: false,
				Product: [{
					productName: '茶叶',
					productPicture: '图片',
					specModel: '500g',
					deliveryQuantity: '1000g',
					productUnit: 'g',
					commodityNote: '常温保存'
				}],
				SelectList: [],
				select: false,
				ruleForm: {
					stockOutDocunum: '',
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
				rules: {
					stockOutDocunum: [{
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
					}]
				}
			};
		},
		methods: {
			selectChange(val) {

			},
			queryWarehouse() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/stockOut'
				}).then(res => {
					this.SelectList = res.data
				}).catch(err => {
					console.log(err)
				})
			},
			addOdo() {
				this.axios({
					method: 'post',
					url: 'http://localhost:8089/eims/stockOut',
				}).then(res => {
					console.log(res)
					//this.ruleForm=this.data
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
				if (rows.length > 1) { //若删除的行是表格的仅存的最后一行,则不能删除
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
					url: 'http://localhost:8089/eims/stockOut/one',
					params: {
						"id": this.$route.params.stockOutId
					}
				}).then(res => {
					this.ruleForm = res.data
					this.Product = res.data.outboundDetailList
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
			if (typeof(this.$route.params.stockOutId) == "undefined") {
				this.show()
				
			} else {
				this.show()
			}
		}
	}
</script>
<style>
	#addOdo {
		background-color: aliceblue;
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
		height: 600px;

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
