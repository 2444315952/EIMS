<template>
	<div id="Addblitem">
		<el-container>
			<el-container>
				<el-main>
					<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
						<el-row></el-row>
						<el-row></el-row>
						<el-row>
							<el-col :span="2">
								<el-form-item label="调拨单号:" prop="requisition">
								</el-form-item>
							</el-col>
							<el-col :span="6">
								<el-input width="250px" v-model="ruleForm.requisition"></el-input>
							</el-col>
							<el-col :span="1"></el-col>
							<el-col :span="15">
								<el-form-item label="单据日期:" required>
									<el-col :span="11">
										<el-form-item prop="date1">
											<el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1"
												style="width: 100%;"></el-date-picker>
										</el-form-item>
									</el-col>
									<el-col class="line" :span="2">-</el-col>
									<el-col :span="11">
										<el-form-item prop="date2">
											<el-time-picker placeholder="选择时间" v-model="ruleForm.date2"
												style="width: 100%;">
											</el-time-picker>
										</el-form-item>
									</el-col>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="2">
								<span class="dialog-footer">
									<el-form-item label="盘点仓库:" prop="warehouseName"></el-form-item>
								</span>
							</el-col>
							<el-col :span="7">
								<el-select v-model="ruleForm.export" placeholder="请选择"
									style="width: 275px;float: left;">
									<el-option label="默认仓" value="shanghai"></el-option>
									<el-option label="深圳分店" value="beijing"></el-option>
								</el-select>
							</el-col>
							<el-col :span="2">
								<el-form-item label="业务员:" prop="requisition">
								</el-form-item>
							</el-col>
							<el-col :span="6">
								<el-input width="100px" v-model="ruleForm.EmployeeName"></el-input>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="1"></el-col>
							<el-col :span="22">
								<el-table ref="multipleTable" :data="Product" :height="tableHeight"
									tooltip-effect="dark" style="width: 100%; text-align :center;"
									@selection-change="handleSelectionChange">
									<el-table-column label="操作" width="100">
										<template #default="scope">
											<el-button icon="el-icon-circle-plus" size="mini"
												@click="addrow(scope.$index)" circle></el-button>
											<el-button icon="el-icon-remove" size="mini"
												@click="deleteRow(scope.$index,Product)" circle>
											</el-button>
										</template>
									</el-table-column>
									<el-table-column prop="SpecModel" label="产品图片" width="80">
									</el-table-column>
									<el-table-column label="产品名称" width="180">
										<template #default="append">
											<el-input v-model="ruleForm.ProductName" placeholder="请输入产品名称">
												<template #suffix>
													<el-button icon="el-icon-more" style="border: none;" size="small"
														@click="setProduct = true"></el-button>

												</template>
											</el-input>
										</template>
									</el-table-column>
									<el-table-column prop="SpecModel" label="产品规格" width="90">
									</el-table-column>
									<el-table-column prop="Unit" label="单位" width="90">
									</el-table-column>
									<el-table-column prop="OutboundQuantity" label="库存数量" width="90" show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="盘点数量" width="90" show-overflow-tooltip>
										<el-input v-model="ruleForm.OutboundQuantity"> </el-input>
									</el-table-column>
									<el-table-column prop="OutboundQuantity" label="盈亏数" width="90" show-overflow-tooltip>
									</el-table-column>
									<el-table-column label="商品行备注" width="205" show-overflow-tooltip>
										<el-input v-model="ruleForm.CommodityNote"> </el-input>
									</el-table-column>

								</el-table>
							</el-col>
							<el-col :span="1"></el-col>
						</el-row>
						<el-row></el-row>
						<el-row></el-row>
						<el-row></el-row>
						<el-row>
							<el-col :span="8">
								<el-form-item label="单据备注:" prop="DocumentsNote">
									<el-input type="textarea" v-model="ruleForm.DocumentsNote"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row></el-row>
						<el-row></el-row>
						<el-row></el-row>
						<el-form-item>
							<el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
							<el-button @click="resetForm('ruleForm')">取消</el-button>
						</el-form-item>
					</el-form>

					<el-dialog title="选择商品" v-model="setProduct" :width="'730px'">
						<span class="font-style">商品:</span>&nbsp;
						<el-input class="inline-input" placeholder="请输入单据编号或备注" style="width: 240px;" v-model="project"
							size="medium">
							<template #append>
								<el-button icon="el-icon-search" size="small" @click="search"></el-button>
							</template>
						</el-input>
						<el-row></el-row>
						<el-row></el-row>
						<el-row>
							<el-col :span="1"></el-col>
							<el-col :span="22">
								<el-table :data="Product" tooltip-effect="dark" style="width: 100%; text-align: center;"
									max-height="500" @selection-change="handleSelectionChange">
									<el-table-column type="selection" width="55">
									</el-table-column>
									<el-table-column label="产品图片" width="150" property="ProductName">
									</el-table-column>
									<el-table-column label="产品名称" width="155" property="ProductName">
									</el-table-column>
									<el-table-column label="产品规格" width="100" property="SpecModel">
									</el-table-column>
									<el-table-column label="产品数量" property="ProductNumber" width="100"
										show-overflow-tooltip>
										<el-input v-model="ruleForm.ProductNumber"> </el-input>
									</el-table-column>
									<el-table-column property="Unit" label="产品单位" width="100" show-overflow-tooltip>
									</el-table-column>
								</el-table>
							</el-col>
							<el-col :span="1"></el-col>
						</el-row>
						<template #footer>
							<span class="dialog-footer">
								<el-button @click="setProduct = false">取 消</el-button>
								<el-button type="primary" @click="setProduct = false">确定</el-button>
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
				Product: [{
					number: '1',
					ProductName: '2016-05-02',
					SpecModel: '王小虎',
					ProductNumber: '上海市普陀区金沙江路 1518 弄',
					Unit: '1',
				}, {
					number: '2',
					ProductName: '2016-05-02',
					SpecModel: '王小虎',
					ProductNumber: '上海市普陀区金沙江路 1518 弄',
					Unit: '2',
				}],
				setProduct: false,
				ruleForm: {
					number: '',
					requisition: '',
					export: '',
					fold: '',
					OutboundQuantity: '',
					ProductNumber: '',
					CommodityNote: '',
					date1: '',
					date2: '',
					delivery: false,
					DocumentsNote: ''
				},
				rules: {
					requisition: [{
							required: true,
							message: '请输入单据编号',
							trigger: 'blur'
						},
						{
							min: 3,
							max: 5,
							message: '长度在 3 到 5 个字符',
							trigger: 'blur'
						}
					],
					export: [{
						required: true,
						message: '请选择调出仓库',
						trigger: 'change'
					}],
					fold: [{
						required: true,
						message: '请选择调入仓库',
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
					}]
				}
			};
		},
		methods: {
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
			//表格增加行
			addrow(index) {
				var list = {
					number: '1',
					ProductName: '2016-05-02',
					SpecModel: '王小虎',
					ProductNumber: '上海市普陀区金沙江路 1518 弄',
					Unit: '1',
				}
				this.Product.splice(index + 1, 0, list)
			},

			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
</script>
<style>
	#Addblitem {
		/* background-color: aliceblue; */
	}

	#Addblitem .el-container {
		height: 100%;
		width: 100%;
	}

	#Addblitem .el-header,
	#Addblitem .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	#Addblitem .el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}

	#Addblitem .el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		height: 600px;

	}


	#Addblitem .el-row {
		margin-bottom: 5px;
	}

	#Addblitem .el-col {
		border-radius: 4px;
	}

	#Addblitem .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

	#Addblitem .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	/* .el-input__inner{
			width: 200px;
		} */
	# .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}

	#Addblitem .el-dialog__body {
		padding: 5px;
	}

	#Addblitem .el-table td,
	#Addblitem .el-table th {
		padding: 6px 0;
	}
</style>
