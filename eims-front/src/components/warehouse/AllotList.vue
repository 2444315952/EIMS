<template>
	<div id="Allot">
		<el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
			class="demo-ruleForm">
			<el-row>
				<el-breadcrumb separator-class="el-icon-arrow-right">
					<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item><a href="/">调拨单列表</a></el-breadcrumb-item>
				</el-breadcrumb>
			</el-row>
			<el-container style="background-color#F9FAFC;padding-top: 15px;">
				<el-main style="background-color:#F9FAFC">
					<el-row>
						<el-col :span="5">
							<el-input class="inline-input" placeholder="请输入单据编号/业务员"
								style="width: 235px; float: left;" v-model="AllotInput" @keyup.enter.native="AllotSearch"
								size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" type="primary" @click="AllotSearch"></el-button>
								</template>
							</el-input>
						</el-col>
						<el-col :span="5">
							<el-form-item label="调出仓:" prop="foldWarehouseId" label-width="60px">
								<el-select v-model="ruleForm.foldWarehouseId" @change="show()"
									@click="queryFoldWarehouse()" :disabled="isdisabled" placeholder="请选择"
									style="width: 150px;float: left;">
									<el-option label="全部仓库" value=""></el-option>
									<el-option v-for="item in SelecFoltList" :label="item.warehouseName"
										:value="item.warehouseId"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="5">
							<el-form-item label="调入仓:" prop="exportWarehouseId" label-width="60px">
								<el-select v-model="ruleForm.exportWarehouseId" @change="show()"
									@click="queryExportWarehouse()" :disabled="isdisabled" placeholder="请选择"
									style="width: 150px;float: left;">
									<el-option label="全部仓库" value=""></el-option>
									<el-option v-for="item in SelectExporList" :label="item.warehouseName"
										:value="item.warehouseId"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<el-col :span="5"></el-col>
						<el-col :span="2">
							<el-button size="medium" type="danger" v-show="delbut"
								@click="handleDelete(handleSelectionChange)">批量删除
							</el-button>
						</el-col>
						<el-col :span="2">
							<el-button @click="this.$router.push({name:'AddAllot'})" style="float: right;"
								type="primary" size="medium">新增
							</el-button>
						</el-col>
					</el-row>
					<el-table ref="multipleTable" :data="tableData" :height="tableHeight" tooltip-effect="dark"
						style="width: 100%; height: 480px;" @selection-change="handleSelectionChange">
						<el-table-column type="selection" width="55">
						</el-table-column>
						<el-table-column prop="transferDocunum" label="单据编号" width="165">
						</el-table-column>
						<el-table-column :formatter="dateFormat" prop="documentDate" label="单据日期" width="140">
						</el-table-column>
						<el-table-column prop="foldWarehouseName" label="调出仓库" width="90">
						</el-table-column>
						<el-table-column prop="exportWarehouseName" label="调入仓库" width="90">
						</el-table-column>
						<el-table-column label="审核状态" width="90" show-overflow-tooltip>
							<template #default="scope">
								<p v-if="tableData[scope.$index].audited==0">未审核</p>
								<p v-if="tableData[scope.$index].audited==1">已审核</p>
								<p v-if="tableData[scope.$index].audited==2">被驳回</p>
							</template>
						</el-table-column>
						<el-table-column label="调拨状态" width="90" show-overflow-tooltip>
							<template #default="scope">
								<p v-if="tableData[scope.$index].transferState==0">未调拨</p>
								<p v-if="tableData[scope.$index].transferState==1">已调拨</p>
							</template>
						</el-table-column>
						<!-- <el-table-column prop="workPointName" label="工作点" width="70" show-overflow-tooltip>
						</el-table-column> -->
						<el-table-column prop="employeeName" label="业务员" width="90">
						</el-table-column>
						<el-table-column prop="documentsNote" label="备注" width="165" show-overflow-tooltip>
						</el-table-column>
						<el-table-column prop="operate" label="操作" show-overflow-tooltip>
							<template #default="scope">
								<el-tooltip class="item" effect="dark" content="查看" placement="top">
									<el-button size="mini" circle type="success" icon="el-icon-view" content="查看"
										@click="$router.push({name:'AddAllot',params:{transferId:scope.row.transferId}})">
									</el-button>
								</el-tooltip>
								<el-tooltip class="item" effect="dark" v-if="scope.row.audited==0 || scope.row.audited==2" content="编辑"
									placement="top">
									<el-button size="mini" circle type="primary" icon="el-icon-edit-outline"
										@click="$router.push({name:'AddAllot',params:{transferId:scope.row.transferId}})">
									</el-button>
								</el-tooltip>
								<el-tooltip class="item" effect="dark" v-if="scope.row.audited==0 || scope.row.audited==2" content="审核"
									placement="top">
									<el-button size="mini" type="info" circle icon="el-icon-s-check"
										@click="checkOut(scope.row.transferId)">
									</el-button>
								</el-tooltip>
								<el-tooltip v-if="scope.row.audited==0" class="item" effect="dark" content="驳回"
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
						:total="queryForm.total" @size-change="handleSizeChange"
						@current-change="handleCurrentChange" />
				</el-main>
			</el-container>
		</el-form>
	</div>
</template>


<script>
	import moment from 'moment'
	export default {
		data() {
			return {
				SelecFoltList: [],
				foldWarehouseNameSel: '',
				exportWarehouseNameSel: '',
				ruleForm: {
					exportWarehouseId: '',
					foldWarehouseId: '',
					transferDetailsList: [{}]
				},
				SelectExporList: [],
				warehouseSelectValue: [],
				warehouseSelect: '',
				queryForm: {
					pageNum: 1,
					pageSize: 10,
					total: '',
					//workPointId: 1,
					//currentPage,pagesize:对应后端的
				},
				AllotInput: '',
				delbut: false,
				tableHeight: window.innerHeight,
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
			//调入仓库下拉框
			selectExportWarehouse(val) {

			},

			//下拉框查询调出仓库的值
			queryFoldWarehouse() {
				this.queryType = 'fold'
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/warehouse/search',
				}).then(res => {
					console.log(res)
					this.SelecFoltList = res.data.list
					this.ruleForm.foldWarehouseName = this.ruleForm.SelecFoltList[0].warehouseId
				}).catch(err => {

				})
			},

			//下拉框查询调入仓库的值
			queryExportWarehouse() {
				this.queryType='export'
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/warehouse',
				}).then(res => {
					console.log(res)
					this.SelectExporList = res.data.list
					this.queryForm.exportWarehouseName = this.ruleForm.SelectExporList[0].warehouseId
				}).catch(err => {

				})
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
						url: "http://localhost:8089/eims/transfer/check",
						method: "put",
						params: {
							"transferId": val
						}
					}).then(res => {
						this.show()
					}).catch(err => {

					})
					this.$message({
						type: 'success',
						message: '审核成功,已生成出入库记录!'
					});
					this.show()
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消审核'
					});
				});
			},
			//驳回
			backCheck(val) {
				//this.dialogFormVisible = true
				this.$prompt('请输入驳回原因', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					inputPattern:/\S+/,
					inputErrorMessage:'请输入驳回原因！'
				}).then((value) => {
					
					this.axios({
						url: "http://localhost:8089/eims/transfer",
						method: "put",
						data: {
							"transferId": val,
							"audited": 2,
							"reason":value.value
						}
					}).then(res => {
						console.log("审核状态信息是：")
						console.log(res)
						//this.ruleForm.reason = res.data.list
						this.show()
					}).catch(err => {
			
					})
					this.$message({
						type: 'success',
						message: '驳回成功!'
					});
					this.show()
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消驳回操作'
					});
				});
			},
			
			handleEdit(index, row) {
				console.log(index, row);
			},
			handleSizeChange(val) {
				this.loading = true
				this.queryForm.pageSize = val
				if (this.queryType =='all')
					this.show()
				else if (this.queryType =='search')
					this.AllotSearch()
				else if (this.queryType =='fold')
					this.queryFoldWarehouse()
				else if (this.queryType =='export')
					this.queryExportWarehouse()
			},
			handleCurrentChange(val) {
				this.loading = true
				this.queryForm.pageNum = val
				if (this.queryType == 'all')
					this.show()
				else if (this.queryType == 'search')
					this.AllotSearch()
				else if (this.queryType == 'fold')
					this.queryFoldWarehouse()
				else if (this.queryType == 'export')
					this.queryExportWarehouse()
			},

			AllotSearch() {
				this.queryType = 'search'
				this.queryForm.transferDocunum = this.AllotInput
				this.queryForm.employeeName = this.AllotInput
				//console.log(this.queryForm)
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/transfer/search',
					params: this.queryForm
				}).then(res => {
					console.log("reanfer:")
					console.log(res)
					this.queryForm.total = res.data.total
					this.tableData = res.data.list
				})
			},
			show() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/transfer/screen',
					params: {
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize,
						"foldWarehouseId": this.ruleForm.foldWarehouseId,
						"exportWarehouseId": this.ruleForm.exportWarehouseId
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
