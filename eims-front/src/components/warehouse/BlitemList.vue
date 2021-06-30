<template>
	<div id="Blitem">
		<el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
			class="demo-ruleForm">
			<el-row>
				<el-breadcrumb separator-class="el-icon-arrow-right">
					<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>盘点单列表</el-breadcrumb-item>
				</el-breadcrumb>
			</el-row>
			<el-container style="background-color#F9FAFC;padding-top: 15px;">
				<el-main style="background-color:#F9FAFC">
					<el-row>
						<el-col :span="5">
							<el-input style="width: 260px; float: left" class="inline-input" placeholder="请输入盘点单号/业务员"
								v-model="SearInput" @keyup.enter.native="SearachFor" size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" @click="SearachFor"></el-button>
								</template>
							</el-input>
						</el-col>
						<el-col :span="6">
							<el-form-item label="盘点仓库:" prop="warehouseId" label-width="90px">
								<el-select v-model="ruleForm.warehouseId" @change="show()" @click="queryWarehouse()"
									:disabled="isdisabled" placeholder="请选择" style="width: 150px;float: left;">
									<el-option label="全部仓库" value=""></el-option>
									<el-option v-for="item in SelectList" :label="item.warehouseName"
										:value="item.warehouseId"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
						<!-- <el-col :span="8">
							<span class="dialog-footer" style="float:left;">
								<span class="font-style">单据日期:</span>&nbsp;
								<el-date-picker
								      v-model="value1"
								      type="date"
									  size="medium"
								      placeholder="选择日期">
								</el-date-picker>
							</span>
						</el-col> -->
						<el-col :span="8"></el-col>
						<el-col :span="3">
							<el-button size="medium" type="danger" v-show="delbut" @click="Pldelete(SelectFun)">批量删除
							</el-button>
						</el-col>
						<el-col :span="2">
							<el-button @click="this.$router.push({name:'Addblitem'})" style="float: right;" size="medium"
								type="primary">新增
							</el-button>
						</el-col>
					</el-row>
					<el-table ref="multipleTable" :data="tableData" :height="tableHeight" tooltip-effect="dark"
						max-height="435" style="width: 100%; height:435px;" size="medium" @selection-change="SelectFun">
						<el-table-column type="selection" width="55">
						</el-table-column>
						<el-table-column prop="blitemDocunum" label="单据编号" width="190">
						</el-table-column>
						<el-table-column :formatter="dateFormat" label="单据日期" width="160" prop="documentDate">
						</el-table-column>
						<el-table-column prop="warehouseName" label="盘点仓库" width="130" show-overflow-tooltip>
						</el-table-column>
						<el-table-column label="审核状态" width="130" show-overflow-tooltip>
							<template #default="scope">
								<p v-if="tableData[scope.$index].audited==0">未审核</p>
								<p v-if="tableData[scope.$index].audited==1">已审核</p>
								<p v-if="tableData[scope.$index].audited==2">被驳回</p>
							</template>
						</el-table-column>
						<el-table-column prop="employeeName" label="业务员" width="130">
						</el-table-column>
						<el-table-column prop="documentsNote" label="备注" width="230" show-overflow-tooltip>
						</el-table-column>
						<el-table-column prop="operate" label="操作" show-overflow-tooltip>
							<template #default="scope">
								<el-tooltip class="item" effect="dark" content="查看" placement="top">
									<el-button size="mini" circle type="success" icon="el-icon-view" content="查看"
										@click="$router.push({name:'Addblitem',params:{blitemId:scope.row.blitemId}})">
									</el-button>
								</el-tooltip>
								<el-tooltip class="item" v-if="scope.row.audited==0 || scope.row.audited==2" effect="dark" content="编辑"
									placement="top">
									<el-button size="mini" circle type="primary" icon="el-icon-edit-outline"
										@click="$router.push({name:'Addblitem',params:{blitemId:scope.row.blitemId}})">
									</el-button>
								</el-tooltip>
								<el-tooltip class="item" v-if="scope.row.audited==0 || scope.row.audited==2" effect="dark" content="审核"
									placement="top">
									<el-button size="mini" type="info" circle icon="el-icon-s-check"
										@click="check(scope.row.blitemId)">
									</el-button>
								</el-tooltip>
								<el-tooltip class="item" v-if="scope.row.audited==0" effect="dark" content="驳回"
									placement="top">
									<el-button size="mini" type="info" circle icon="el-icon-s-check"
										@click="backCheck(scope.row.blitemId)">
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
		</el-form>
	</div>
</template>


<script>
	import qs from 'qs'
	import moment from 'moment'
	export default {
		data() {
			return {
				queryForm: {
					pageNum: 1,
					pageSize: 10,
					total: ''
					//workPointId: 1
					//currentPage,pagesize:对应后端的
				},
				SelectList: [],
				ruleForm:{
					warehouseId:'',
					reason: '',
					employeeId:'',
				},
				queryType: 'all',
				currentPage: 1,
				tableHeight: window.innerHeight,
				value1: '',
				defaultTime1: [
					new Date(2000, 1, 1, 12, 0, 0),
				],
				options: [{
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
				options1: [{
						value: 'all',
						label: '全部类型'
					},
					{
						value: 'model',
						label: '以收取货单出库'
					},
					{
						value: 'machine',
						label: '退货购单出库'
					},
					{
						value: 'model',
						label: '调拨出库'
					},
					{
						value: 'model',
						label: '盘亏出库'
					},
				],
				delbut: false,
				SearInput:'',
				valuetype: 'all',
				valuetype1: 'all',
				tableData: [{}],
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
			dateFormat(row, column) {
				var date = row[column.property];
				if (date == undefined) {
					return ''
				};
				return moment(date).format("YYYY-MM-DD HH:mm")
			},

			handleSizeChange(val) {
				this.loding = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all')
					this.show()
				else if (this.queryType == 'search')
					this.SearachFor()
			},
			handleCurrentChange(val) {
				this.loding = true
				this.queryForm.pageNum = val
				if (this.queryType == 'all')
					this.show()
				else if (this.queryType == 'search')
					this.SearachFor()
			},
			//下拉框查询所有仓库
			queryWarehouse() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/warehouse'
				}).then(res => {
					this.SelectList = res.data.list
					this.queryForm.warehouseName = this.SelectList[0].warehouseId
				}).catch(err => {
					console.log(err)
				})
			},

			//搜索框查询
			SearachFor() {
				this.queryType = 'search'
				this.queryForm.blitemDocunum = this.SearInput
				this.queryForm.employeeName = this.SearInput
				this.axios({
					url: 'http://localhost:8089/eims/blitem/search',
					method: 'get',
					params: this.queryForm
				}).then(res => {
					console.log(res)
					this.queryForm.total = res.data.total
					this.tableData = res.data.list
				}).catch(err => {

				})

			},

			//审核盘点单
			check(val) {
				this.$confirm('将要对该出库单进行审核操作, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						url: "http://localhost:8089/eims/blitem/audited",
						method: "put",
						params: {
							"blitemId": val,
							// "audited": 1
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
				//this.dialogFormVisible = true
				this.$prompt('请输入驳回原因', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					inputPattern:/\S+/,
					inputErrorMessage:'请输入驳回原因！'
				}).then((value) => {
					this.axios({
						url: "http://localhost:8089/eims/blitem",
						method: "put",
						data: {
							"blitemId":val,
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
			

			//判断批量删除按钮是否显示
			SelectFun(val) {
				this.multipleSelection = val
				if (this.multipleSelection.length <= 0) {
					this.delbut = false
				} else {
					this.delbut = true
				}
			},

			//批量删除
			Pldelete() {
				for (var i = 0; i < this.multipleSelection.length; i++) {
					if (this.multipleSelection[i].audited == 1) {
						this.$message({
							type: 'info',
							message: '已审核的盘点单不能进行删除操作!'
						})
						return false
					}
				}

				this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】张盘点单, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					var Ids = []
					this.multipleSelection.forEach(item => {
						Ids.push(item.blitemId);
					})
					this.axios({
						url: "http://localhost:8089/eims/blitem/batch",
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

			handleEdit(index, row) {
				console.log(index, row);
			},
			handleDelete(index, row) {
				console.log(index, row);
			},
			toggleSelection(rows) {
				if (rows) {
					rows.forEach(row => {
						this.$refs.multipleTable.toggleRowSelection(row);
					});
				} else {
					this.$refs.multipleTable.clearSelection();
				}
			},
			handleSelectionChange(val) {
				this.multipleSelection = val;
			},
			show() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/blitem/screen',
					params: {
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize,
						"warehouseId": this.ruleForm.warehouseId
					}
				}).then(res => {
					console.log(res)
					this.queryForm.total = res.data.total
					this.tableData = res.data.list
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
	#Blitem .el-container {
		height: 100%;
		width: 100%;
	}

	#Blitem .el-header,
	#Blitem .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	#Blitem .el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}

	#Blitem .el-main {
		/* background-color: #E9EEF3; */
		color: #333;
		text-align: center;
		height: 100%;

	}

	#Blitem .el-row {
		margin-bottom: 5px;
	}

	#Blitem .el-col {
		border-radius: 4px;
	}

	#Blitem .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

	#Blitem .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	#Blitem .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}

	#Blitem .el-dialog__body {
		padding: 5px;
	}

	#Blitem .el-table td,
	#Blitem .el-table th {
		padding: 6px 0;
	}
</style>
