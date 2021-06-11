<template>
	<div id="Inquiry">
		<el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
			class="demo-ruleForm">

			<el-row>
				<el-col :span="12">
					<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
						<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
						<el-breadcrumb-item><a href="/InquiryList">询价单列表</a></el-breadcrumb-item>
						<el-breadcrumb-item><a href="/">询价单</a></el-breadcrumb-item>
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
							<el-form-item label="单据编号" style="float: left;" prop="name">
								<el-input v-model="ruleForm.name" size="medium" disabled></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="8">
							<el-form-item label="单据日期" style="float: left;" required>
								<el-form-item prop="date1">
									<el-date-picker type="date" placeholder="选择日期" size="medium"
										v-model="ruleForm.date1" style="width: 206px;"></el-date-picker>
								</el-form-item>
							</el-form-item>
						</el-col>

						<el-col :span="8">
							<el-form-item label="业务员" style="float: left;" required>
								<el-select v-model="ruleForm.date1" style="width: 206px;" placeholder="请选择业务员">
									<el-option label="业务员1" value="shanghai"></el-option>
									<el-option label="业务员2" value="beijing"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="询价发起者" style="float: left;" prop="name">
								<el-input v-model="ruleForm.name" size="medium" disabled>
								</el-input>
							</el-form-item>
						</el-col>
						
						<el-col :span="8">
							<el-form-item label="询价接受者" style="float: left;" prop="name">
								<el-input v-model="ruleForm.name" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="companyDialogVisible = true"></el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>

						<el-dialog title="公司" v-model="companyDialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="companySearchContent" placeholder="请搜索公司名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" size="mini"></el-button>
										</template>
									</el-input>
								</el-col>

							</el-row>

							<el-table :data="company" max-height="300" style="height: 300px;" highlight-current-row
								@current-change="companySelectionChange">
								<el-table-column property="companyName" label="公司名称"></el-table-column>
								<el-table-column property="contactAddress" label="联系地址"></el-table-column>
								<el-table-column property="contactNumber" label="联系电话"></el-table-column>
								<el-table-column property="contactEmail" label="联系邮箱"></el-table-column>
							</el-table>
						</el-dialog>
						
						<el-dialog title="工作点" v-model="workPointDialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="workPointSearchContent" placeholder="请搜索工作点名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" size="mini"></el-button>
										</template>
									</el-input>
								</el-col>
						
							</el-row>
						
							<el-table :data="workPoint" max-height="300" style="height: 300px;" highlight-current-row
								@current-change="workPointSelectionChange">
								<el-table-column property="supplierName" label="工作点名称"></el-table-column>
								<el-table-column property="contactAddress" label="联系地址"></el-table-column>
								<el-table-column property="contactNumber" label="联系电话"></el-table-column>
								<el-table-column property="contactEmail" label="联系邮箱"></el-table-column>
								<el-table-column property="companyName" label="所属公司"></el-table-column>
							</el-table>
							
							<template #footer>
								<span class="dialog-footer">
									<el-button @click="workPointDialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="workPointDialogVisible = false" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>

					</el-row>

					<el-table :data="tableData" max-height="402"
						style="width: 100%;height:402px;">
						<el-table-column label="产品名称" prop="productName">
							<template #default="scope">
								<el-input v-model="tableData[scope.$index].productName" style="width: 170px" size="small" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="mini" @click="productDialogVisible = true">
										</el-button>
									</template>
								</el-input>
							</template>
						</el-table-column>
						<el-table-column label="规格型号" prop="specModel">
						</el-table-column>
						<el-table-column label="产品单位" prop="productUnit">
						</el-table-column>
						<el-table-column label="采购数量" prop="job">
							<template #default="scope">
								<el-input-number v-model="tableData[scope.$index].purchaseQuantity" size="small" :min="0" :precision="0">
								</el-input-number>
							</template>
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

						<el-table :data="product" max-height="300" style="height: 300px;"
							@current-change="productSelectionChange">
							<el-table-column type="selection" width="55">
							</el-table-column>
							<el-table-column property="productName" label="产品名称"></el-table-column>
							<el-table-column property="specModel" label="规格型号"></el-table-column>
							<el-table-column property="productUnit" label="产品单位"></el-table-column>
							<el-table-column property="referCost" label="参考成本价"></el-table-column>
							<el-table-column property="marketPrice" label="市场价"></el-table-column>
						</el-table>
						<template #footer>
							<span class="dialog-footer">
								<el-button @click="productDialogVisible = false" size="medium">取 消</el-button>
								<el-button type="primary" @click="productDialogVisible = false" size="medium">确 定
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
		name: "Inquiry",
		data() {
			return {
				ruleForm: {
					name: 1,
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				},
				rules: {
					name: [{
							required: true,
							message: '请输入活动名称',
							trigger: 'blur'
						},
						{
							min: 3,
							max: 5,
							message: '长度在 3 到 5 个字符',
							trigger: 'blur'
						}
					],
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
				tableData: [{'productName':'产品1','specModel':'规格1','productUnit':'单位1','purchasePrice':10,'purchaseQuantity':2,purchaseSubtotal:20}],
				supplier: [],
				supplierSearchContent: '',
				supplierDialogVisible: false,
				company:[{'companyName':'公司1','contactAddress':'联系地址1','contactNumber':'联系电话1','contactEmail':'联系邮箱1'}],
				companySearchContent:'',
				companyDialogVisible:false,
				workPoint:[],
				workPointSearchContent:'',
				workPointDialogVisible:false,
				product: [],
				productSearchContent: '',
				productDialogVisible: false
			}
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
			addRow() {
				this.tableData.push({});
			},
			removeRow(index) {
				if (this.tableData.length > 1)
					this.tableData.splice(index, 1);
			},
			warehouseSelectionChange() {

			},
			productSelectionChange() {

			},companySelectionChange(val) {
				this.companyDialogVisible = false
				this.workPointDialogVisible = true
			},workPointSelectionChange(){
				
			}
		},created(){
		}
	}
</script>

<style>
	/* 表头与表行对齐 */
	#Inquiry .cell {
		padding-left: 0px;
	}

	/* 加号按钮 */
	#Inquiry .el-input-group__append,
	.el-input-group__prepend {
		padding: 0px 18px;
	}

	#Inquiry .el-table .el-input-group__append,
	.el-input-group__prepend {
		padding: 0px 15px;
	}

	#Inquiry .el-table {
		padding: 0px 10px;
	}

	#Inquiry .el-table td,
	#Inquiry .el-table th {
		padding: 6px 0px;
	}

	#Inquiry .el-footer {
		padding-bottom: 20px;
	}

	#Inquiry .el-main {
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

	#Inquiry .el-dialog .el-dialog__body {
		padding: 20px;
	}
</style>
