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
							<el-form-item label="单据编号:" prop="transferDocunum">
								<el-input v-model="ruleForm.transferDocunum" :disabled="true"
									style="float: left; width: 250px;"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="单据日期:" prop="documentDate">
								<el-date-picker type="datetime" placeholder="选择日期" v-model="ruleForm.documentDate"
									:disabled="isdisabled" style="width:250px; float: left;"></el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="1"></el-col>
						<el-col :span="7">
							<el-form-item label="业务员:" prop="employeeId">
								<el-select v-model="ruleForm.employeeName" @change="selectEmployee"
									:disabled="isdisabled" placeholder="请选择" @click="queryEmployee()"
									style="float:left; width: 250px;">
									<el-option v-for="item in employeeList" :label="item.employeeName"
										:value="item.employeeName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="调出仓库:" prop="warehouseId">
								<el-select v-model="ruleForm.warehouseName" @change="selectWarehouse"
									@click="queryWarehouse()" :disabled="isdisabled" placeholder="请选择"
									style="width: 250px;float: left;">
									<el-option v-for="item in SelectList" :label="item.warehouseName"
										:value="item.warehouseName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="9">
							<el-form-item label="调入仓库:" prop="warehouseId">
								<el-select v-model="ruleForm.warehouseName" @change="selectWarehouse"
									@click="queryWarehouse()" :disabled="isdisabled" placeholder="请选择"
									style="width: 250px;float: left;">
									<el-option v-for="item in SelectList" :label="item.warehouseName"
										:value="item.warehouseName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="6">
						</el-col>
					</el-row>
					<br/>
					<el-row>
						<el-col :span="1"></el-col>
						<el-col :span="22">
							<el-table ref="multipleTable" :data="ruleForm.transferDetailsList" :height="tableHeight"
								max-height="230" tooltip-effect="dark"
								style="width: 100%; text-align :center; height: 230px;"
								@selection-change="handleSelectionChange">
								<el-table-column label="产品名称" width="280">
									<template #default="append">
										<el-input v-model="ruleForm.productName" placeholder="请选择要调拨的产品">
											<template #suffix>
												<el-button icon="el-icon-more" style="border: none;" size="small"
													@click="setProduct = true"></el-button>

											</template>
										</el-input>
									</template>
								</el-table-column>
								<el-table-column prop="SpecModel" label="产品图片" width="160">
								</el-table-column>
								<el-table-column prop="specModel" label="产品规格" width="130">
								</el-table-column>
								<el-table-column prop="productUnit" label="单位" width="130">
								</el-table-column>
								<el-table-column label="调拨数量" width="130" prop="outboundQuantity" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="备注" width="320" show-overflow-tooltip>
									<template #default="scope">
										<el-input v-model="ruleForm.transferDetailsList[scope.$index].commodityNote">
										</el-input>
									</template>
								</el-table-column>
							</el-table>
						</el-col>
						<el-col :span="1"></el-col>
					</el-row>
					<br/>
					<br/>
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
	</div>
</template>


<script>
	export default {
		data() {
			return {
				setProduct: false,
				ruleForm: {
					transferDetailsList: [{}],
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
		height: 585px;

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
