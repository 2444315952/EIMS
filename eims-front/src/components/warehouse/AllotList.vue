<template>
	<div id="Allot">
		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/">调拨单列表</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>
		<el-container>
			<el-main>
				<el-row>
					<el-col :span="6">
						<el-input class="inline-input" placeholder="请输入单据编号/工作点/业务员" style="width: 300px; float: left;"
							v-model="SearInput" @keyup.enter.native="SearchFor" size="medium">
							<template #append>
								<el-button icon="el-icon-search" size="small" @click="SearchFor"></el-button>
							</template>
						</el-input>
					</el-col>
					<el-col :span="2">
						<el-button type="text" @click="dialogFormVisible = true" size="medium">高级查询</el-button>

						<el-dialog title="高级查询" v-model="dialogFormVisible">
							<el-form :model="form">
								<el-row>
									<el-form-item label="调拨单号:" :label-width="formLabelWidth" style="width:250px;">
										<el-input v-model="form.number" autocomplete="off"></el-input>
									</el-form-item>
									<el-form-item label="制单人:" :label-width="formLabelWidth">
										<el-input v-model="form.name" autocomplete="off"></el-input>
									</el-form-item>
								</el-row>
								<el-row>
									<el-form-item label="调出仓库:" :label-width="formLabelWidth" style="width:250px;">
										<el-select v-model="form.region" placeholder="请选择活动区域">
											<el-option label="默认仓" value="shanghai"></el-option>
											<el-option label="深圳分店" value="beijing"></el-option>
										</el-select>
									</el-form-item>
									<el-form-item label="调入仓库:" :label-width="formLabelWidth">
										<el-select v-model="form.region" placeholder="请选择活动区域">
											<el-option label="默认仓" value="shanghai"></el-option>
											<el-option label="深圳分店" value="beijing"></el-option>
										</el-select>
									</el-form-item>
								</el-row>
								<el-row>
									<el-form-item label="产品信息:" :label-width="formLabelWidth" style="width: 260px;">
										<el-input v-model="form.product"></el-input>
									</el-form-item>
								</el-row>
								<el-row>
									<el-form-item label="单据日期:" :label-width="formLabelWidth" required
										style="width: 600px;">
										<el-col :span="10">
											<el-date-picker type="date" placeholder="选择日期" v-model="form.date1"
												style="width: 100%;"></el-date-picker>
										</el-col>
										<el-col class="line" :span="2">-</el-col>
										<el-col :span="11">
											<el-time-picker placeholder="选择时间" v-model="form.date2"
												style="width: 100%;"></el-time-picker>
										</el-col>
									</el-form-item>
								</el-row>
								<el-row>
									<el-form-item label="调拨状态:" :label-width="formLabelWidth">
										<el-checkbox-group v-model="form.type">
											<el-checkbox label="全选" name="type"></el-checkbox>
											<el-checkbox label="在途" name="type"></el-checkbox>
											<el-checkbox label="已调拨" name="type"></el-checkbox>
											<el-checkbox label="已作废" name="type"></el-checkbox>
										</el-checkbox-group>
									</el-form-item>
								</el-row>
							</el-form>
							<template #footer>
								<span class="dialog-footer">
									<el-button @click="dialogFormVisible = false">取 消</el-button>
									<el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
								</span>
							</template>
						</el-dialog>
					</el-col>
					<el-col :span="12"></el-col>
					<el-col :span="2">
						<el-button size="medium" type="danger" v-show="delbut"
							@click="handleDelete(handleSelectionChange)">批量删除
						</el-button>
					</el-col>
					<el-col :span="2">
						<el-button @click="this.$router.push({name:'AddAllot'})" style="float: right;" type="primary" size="medium">新增
						</el-button>
					</el-col>
				</el-row>
				<el-table ref="multipleTable" :data="tableData" :height="tableHeight" tooltip-effect="dark"
					style="width: 100%; height: 480px;" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column prop="transferDocunum" label="单据编号" width="170">
					</el-table-column>
					<el-table-column :formatter="dateFormat" label="入库日期" width="140" prop="documentDate">
					</el-table-column>
					<el-table-column prop="foldWarehouseName" label="调入仓库" width="100">
					</el-table-column>
					<el-table-column prop="exportWarehouseName" label="调出仓库" width="100">
					</el-table-column>
					<el-table-column label="审核状态" width="90" show-overflow-tooltip>
						<template #default="scope">
							<p v-if="tableData[scope.$index].audited==0">未审核</p>
							<p v-if="tableData[scope.$index].audited==1">已审核</p>
						</template>
					</el-table-column>
					<el-table-column label="调拨状态" width="90" show-overflow-tooltip>
						<template #default="scope">
							<p v-if="tableData[scope.$index].transferState==0">调拨中</p>
							<p v-if="tableData[scope.$index].transferState==1">已调拨</p>
						</template>
					</el-table-column>
					<el-table-column prop="workPointName" label="工作点" width="100" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="employeeName" label="业务员" width="100">
					</el-table-column>
					<el-table-column prop="documentsNote" label="备注" width="130" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="operate" label="操作" show-overflow-tooltip>
						<template #default="scope">
							<el-tooltip class="item" effect="dark" content="查看" placement="top">
								<el-button size="mini" circle type="success" icon="el-icon-view" content="查看"
									@click="handleEdit(scope.$index, scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip class="item" effect="dark" v-if="scope.row.audited==0" content="审核"
								placement="top">
								<el-button size="mini" type="info" circle icon="el-icon-s-check"
									@click="checkOut(scope.row.transferId)">
								</el-button>
							</el-tooltip>
							<el-tooltip class="item" effect="dark" v-if="scope.row.audited==0" content="编辑"
								placement="top">
								<el-button size="mini" circle type="primary" icon="el-icon-edit-outline"
									@click="handleEdit(scope.$index, scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip v-if="scope.row.audited==1" class="item" effect="dark" content="反审核"
								placement="top">
								<el-button size="mini" type="info" circle icon="el-icon-coordinate"
									@click="backCheck(scope.row.transferId)">
								</el-button>
							</el-tooltip>
						</template>
					</el-table-column>
				</el-table>
				<el-pagination :current-page="queryForm.pageNum" :page-sizes="[10, 20, 40, 60]"
					:page-size="queryForm.pageSize" layout=" total, sizes, prev, pager, next, jumper"
					:total="queryForm.total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
			</el-main>
		</el-container>
	</div>
</template>


<script>
	import moment from 'moment'
	export default {
		data() {
			return {
				queryForm: {
					pageNum: 1,
					pageSize: 10,
					total: '',
					//workPointId: 1,
					//currentPage,pagesize:对应后端的
				},
				SearInput: '',
				delbut: false,
				tableHeight: window.innerHeight,
				warehouse: [{
						value: 'all',
						label: '全部仓库'
					},
					{
						value: 'model',
						label: '默认仓库'
					},
					{
						value: 'machine',
						label: '深圳分店'
					}
				],
				alltype: [{
						value: 'all',
						label: '全部仓库'
					},
					{
						value: 'model',
						label: '默认仓库'
					},
					{
						value: 'machine',
						label: '深圳分店'
					}
				],
				valuetype: 'all',
				valuetype1: 'all',
				queryType: 'all',
				tableData: [],
				multipleSelection: [],
				dialogTableVisible: false,
				dialogFormVisible: false,
				form: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				},
				formLabelWidth: '120px'
			}
		},
		methods: {
			SearchFor() {

			},
			dateFormat(row, column) {
				var date = row[column.property];
				if (date == undefined) {
					return ''
				};
				return moment(date).format("YYYY-MM-DD HH:mm")
			},
			handleSelectionChange(val) {
				this.multipleSelection = val
				if (this.multipleSelection.length <= 0) {
					this.delbut = false
				} else {
					this.delbut = true
				}
			},
			handleDelete() {
				//var isCanDel=true
				for (var i = 0; i < this.multipleSelection.length; i++) {
					if (this.multipleSelection[i].audited == 1) {
						this.$message({
							type: 'info',
							message: '已审核的调拨单不能进行删除操作!'
						});
						return false
					}
				}
				this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】张调拨单, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					var Ids = []
					this.multipleSelection.forEach(item => {
						Ids.push(item.transferId);
					})
					this.axios({
						url: "http://localhost:8089/eims/transfer/batch",
						method: "delete",
						data: Ids
					}).then(res => {
						this.show()
					}).catch(err => {

					})
					this.$message({
						type: 'success',
						message: '删除成功!'
					});
					this.show()
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			checkOut(val) {
				this.$confirm('将进行审核调拨单操作, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						url: "http://localhost:8089/eims/transfer",
						method: "put",
						data: {
							"transferId": val,
							"audited": 1
						}
					}).then(res => {
						this.show()
					}).catch(err => {

					})
					this.$message({
						type: 'success',
						message: '审核成功!'
					});
					this.show()
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消审核'
					});
				});
			},
			backCheck(val) {
				this.$confirm('是否要对该入库单进行反审核操作?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						url: "http://localhost:8089/eims/transfer",
						method: "put",
						data: {
							"transferId": val,
							"audited": 0
						}
					}).then(res => {
						this.show()
					}).catch(err => {

					})
					this.$message({
						type: 'success',
						message: '成功反审核!'
					});
					this.show()
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消反审核操作'
					});
				});
			},
			handleEdit(index, row) {
				console.log(index, row);
			},
			handleSizeChange(val) {
				this.loading = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all')
					this.show()
				else if (this.queryType == 'search')
					this.SearchFor()
				else if (this.queryType == 'screen')
					this.Screen()
			},
			handleCurrentChange(val) {
				this.loading = true
				this.queryForm.pageNum = val
				if (this.queryType == 'all')
					this.show()
				else if (this.queryType == 'search')
					this.SearchFor()
				else if (this.queryType == 'screen')
					this.Screen()
			},
			Screen() {

			},
			SearchFor() {
				this.queryType = 'search'
				this.queryForm.transferDocunum = this.SearInput
				//this.queryForm.warehouseName = this.SearInput
				this.queryForm.workPointName = this.SearInput
				this.queryForm.employeeName = this.SearInput
				this.axios({
					method:'get',
					url:'http://localhost:8089/eims/transfer/search',
					params:this.queryForm
				}).then(res=>{
					this.queryForm.total=res.data.total
					this.tableData=res.data.list
				})
			},
			show() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/transfer',
					params: {
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize,
						//"workPointId": this.queryForm.workPointId
					}
				}).then(res => {
					//this.pageInfo.pageSize=res.data.pageSize
					//this.pageInfo.currentPage=res.data.currentPage
					this.queryForm.total = res.data.total
					this.tableData = res.data.list
					console.log(res)
				}).catch(err => {

				})
			}
		},
		created() {
			this.show()
		}
	}
</script>
<style>
	#Allot {
		/* background-color: aliceblue; */
	}

	#Allot .el-container {
		height: 100%;
		width: 100%;
	}

	#Allot .el-header,
	#Allot .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	#Allot .el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}

	#Allot .el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		height: 600px;

	}


	#Allot .el-row {
		margin-bottom: 5px;
	}

	#Allot .el-col {
		border-radius: 4px;
	}

	#Allot .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

	#Allot .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	/* .el-input__inner{
			width: 200px;
		} */
	#Allot .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}

	#Allot .el-dialog__body {
		padding: 5px;
	}

	#Allot .el-table td,
	#Allot .el-table th {
		padding: 6px 0;
	}
</style>
