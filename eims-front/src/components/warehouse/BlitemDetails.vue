<template>
	<div id="Addblitem">
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/blitem">盘点单列表</a></el-breadcrumb-item>
			<el-breadcrumb-item>盘点单</el-breadcrumb-item>
		</el-breadcrumb>
		<el-container style="padding-top:15px;">
			<el-main style="background-color:#F9FAFC">
				<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="单据编号:" prop="blitemDocunum">
								<el-input v-model="ruleForm.blitemDocunum" :disabled="true"
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
							<el-form-item label="盘点仓库:" prop="warehouseName">
								<el-select v-model="ruleForm.warehouseName" @change="selectFoldWarehouse"
									@click="queryFoldWarehouse()" :disabled="isdisabled" placeholder="请选择"
									style="width: 250px;float: left;">
									<el-option v-for="item in SelecFoltList" :label="item.warehouseName"
										:key="item.value" :value="item.warehouseName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<br/>
					<el-row>
						<!-- <el-col :span="1"></el-col>
						<el-col :span="24"> -->
							<el-table ref="multipleTable" :data="ruleForm.transferDetailsList" :height="tableHeight"
								max-height="265" tooltip-effect="dark" 
								style="width:100%; text-align :center; height: 265px;"
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
								<el-table-column label="产品名称" width="155">
									<template #default="append">
										<el-input v-model="ruleForm.transferDetailsList[append.$index].productName"
											placeholder="请选择产品" :disabled="isdisabled">
											<template #suffix>
												<el-button icon="el-icon-more" style="border: none;" size="small"
													@click="getProduct()" :disabled="isdisabled"></el-button>
					
											</template>
										</el-input>
									</template>
								</el-table-column>
								<el-table-column label="产品图片" width="120">
									<template #default="scope">
										<img v-if="typeof(this.ruleForm.transferDetailsList[0].productId) != 'undefined'"
											:src="ruleForm.transferDetailsList[scope.$index].productPicture"
											style="width: 40px; height: 40px;" />
									</template>
								</el-table-column>
								<el-table-column prop="specModel" label="规格" width="100">
								</el-table-column>
								<el-table-column prop="productUnit" label="单位" width="100">
								</el-table-column>
								<el-table-column label="库存数量" width="100" prop="inventory" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="库存金额" width="100" prop="purchasePrice">
								</el-table-column>
								<el-table-column label="盘点数量" width="160" prop=" physicalInventory" show-overflow-tooltip>
									<template #default="scope">
										<el-input-number
											v-model="ruleForm.transferDetailsList[scope.$index].physicalInventory"
											size="small" :min="1" :precision="0">
										</el-input-number>
									</template>
								</el-table-column>
								<el-table-column label="盘点金额" prop="physicalAmount" width="100" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="数量差额" prop="quantityVariance" width="100" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="差异金额" prop="differenceAmount" width="100" show-overflow-tooltip>
								</el-table-column>
							</el-table>
						<!-- </el-col>
						<el-col :span="1"></el-col> -->
					</el-row>
					<br/>
					<br/>
					<el-row>
						<el-col :span="7">
							<el-form-item label="单据备注:" prop="DocumentsNote">
								<el-input type="textarea" v-model="ruleForm.DocumentsNote"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="2"></el-col>
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
					<br/>
					<el-row>
						<el-col :span="6"></el-col>
						<el-col :span="6">
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
								<el-button @click="resetForm('ruleForm')">取消</el-button>
							</el-form-item>
						</el-col>
						<el-col :span="9"></el-col>
					</el-row>
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
				Product: [{}],
				setProduct: false,
				ruleForm: {
					ProductNumber:1,
					transferDetailsList:[{}],
					blitemDocunum:'',
					warehouseName:'',
					employeeName:'',
					OutboundQuantity: '',
					documentsNote: '',
				},
				rules: {
					blitemDocunum: [{
							required: true,
							message: '请输入盘点单号',
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
						message: '请选择要盘点的仓库',
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
	
	/* #Addblitem .underline-input {
		border: 0px;
		outline: none;
		width: 105px;
		padding: 1px 0px;
		border-bottom: 1px solid rgb(204, 204, 204);
	}
	
	#Addblitem .el-footer {
		padding-bottom: 20px;
	}
	
	
	.underline-input:hover {
		border-bottom: 1px solid rgb(35, 134, 238);
	} */

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
	#Addblitem .font-style {
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
	#Addblitem .el-table th,
	#Addblitem .el-table table{
		padding: 6px 0;
		/* border-color:#606266; */
	}
</style>
