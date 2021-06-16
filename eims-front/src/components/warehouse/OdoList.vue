<template>
	<div id="Odo">
		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" >
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>出库单列表</el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>
		<el-container>
			<el-main>
				<el-row>
					<el-col :span="7">
						<el-input style="width: 320px; float: left" class="inline-input"
							placeholder="请输入单据编号/仓库/工作点/出库类型" v-model="SearInput" @keyup.enter.native="SearachFor"
							size="medium">
							<template #append>
								<el-button icon="el-icon-search" size="small" @click="SearachFor"></el-button>
							</template>
						</el-input>
					</el-col>
					<el-col :span="3">
						<el-button type="text" @click="dialogFormVisible = true" size="medium">高级查询</el-button>

						<el-dialog title="高级查询" v-model="dialogFormVisible">
							<el-form :model="form">
								<el-row>
									<el-form-item label="仓库名:" :label-width="formLabelWidth" style="width:250px;">
										<el-input v-model="form.number" autocomplete="off"></el-input>
									</el-form-item>
									<el-form-item label="入库类型:" :label-width="formLabelWidth" style="width:250px;">
										<el-select v-model="form.region" placeholder="请选择活动区域">
											<el-option label="默认仓" value="shanghai"></el-option>
											<el-option label="深圳分店" value="beijing"></el-option>
										</el-select>
									</el-form-item>
								</el-row>
								<el-row>
									<el-form-item label="单据日期:" :label-width="formLabelWidth" style="width: 600px;">
										<el-col :span="10">
											<el-date-picker type="date" placeholder="选择日期" v-model="form.date1"
												value-format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
										</el-col>
										<el-col class="line" :span="2">-</el-col>
										<el-col :span="11">
											<el-time-picker placeholder="选择时间" v-model="form.date2"
												style="width: 100%;"></el-time-picker>
										</el-col>
									</el-form-item>
								</el-row>
								<el-row>

									<el-form-item label="审核状态:" :label-width="formLabelWidth">
										<el-radio-group v-model="radio1">
											<el-radio :label="1">已审核</el-radio>
											<el-radio :label="2">未审核</el-radio>
										</el-radio-group>
									</el-form-item>
								</el-row>
								<el-row>
									<el-form-item label="出库类型:" :label-width="formLabelWidth">
										<el-radio-group v-model="radio">
											<el-radio :label="1">采购入库</el-radio>
											<el-radio :label="2">退货售单入库</el-radio>
											<el-radio :label="3">调拨入库</el-radio>
											<el-radio :label="4">盘点入库</el-radio>
										</el-radio-group>
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
					<el-col :span="10"></el-col>
					<el-col :span="2">
						<el-button size="medium" type="danger" v-show="delbut" @click="Pldelete(SelectFun)">批量删除
						</el-button>
					</el-col>
					<el-col :span="2">
						<el-button @click="this.$router.push({name:'AddOdo'})" style="float: right;" size="medium"
							type="primary">新增
						</el-button>
					</el-col>
				</el-row>

				<el-table ref="multipleTable" :data="tableData" :height="tableHeight" tooltip-effect="dark"
					style="width: 100%; height: 490px;" @selection-change="SelectFun">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column prop="stockOutDocunum" label="单据编号" width="180">
					</el-table-column>
					<el-table-column :formatter="dateFormat" label="出库日期" width="150" prop="documentDate">
					</el-table-column>
					<el-table-column prop="warehouseName" label="所属仓库" width="110" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="outboundType" label="出库类型" width="110" show-overflow-tooltip>
					</el-table-column>
					<el-table-column label="审核状态" width="100" show-overflow-tooltip>
						<template #default="scope">
							<p v-if="tableData[scope.$index].audited==0">未审核</p>
							<p v-if="tableData[scope.$index].audited==1">已审核</p>
						</template>
					</el-table-column>
					<el-table-column prop="workPointName" label="工作点" width="100" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="employeeName" label="业务员" width="100">
					</el-table-column>
					<el-table-column prop="documentsNote" label="备注" width="160" show-overflow-tooltip>
					</el-table-column>

					<el-table-column prop="operate" label="操作" show-overflow-tooltip>
						<template #default="scope">
							<el-tooltip class="item" effect="dark" content="查看" placement="top">
								<el-button size="mini" circle type="success" icon="el-icon-view" content="查看"
									@click="$router.push({name:'AddOdo',params:{stockOutId:scope.row.stockOutId}})">
								</el-button>
							</el-tooltip>
							<el-tooltip class="item" v-if="scope.row.audited==0" effect="dark" content="编辑"
								placement="top">
								<el-button size="mini" circle type="primary" icon="el-icon-edit-outline"
									@click="$router.push({name:'AddOdo',params:{stockOutId:scope.row.stockOutId}})">
								</el-button>
							</el-tooltip>
							<el-tooltip class="item" effect="dark" v-if="scope.row.audited==0" content="审核"
								placement="top">
								<el-button size="mini" type="info" circle icon="el-icon-s-check"
									@click="check(scope.row.stockOutId)">
								</el-button>
							</el-tooltip>
							<!-- <el-tooltip v-if="scope.row.audited==1" class="item" effect="dark" content="反审核"
								placement="top">
								<el-button size="mini" type="info" circle icon="el-icon-coordinate"
									@click="backCheck(scope.row.stockOutId)">
								</el-button>
							</el-tooltip> -->
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
	import qs from 'qs'
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
				delbut: false,
				SearInput: '', //用户输入的值
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
				audited: [{
					value: 1,
					label: "已审核"
				}, {
					value: 0,
					label: "未审核"
				}],
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
				valuetype: 'all',
				valuetype1: 'all',
				tableData: [],
				multipleSelection: [],
				dialogTableVisible: false,
				dialogFormVisible: false,
				queryType: 'all',
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
			check(val) {
				this.$confirm('将要对该出库单进行审核操作, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.axios({
						url: "http://localhost:8089/eims/stockOut",
						method: "put",
						data: {
							"stockOutId": val,
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
			// backCheck(val) {
			// 	this.$confirm('是否要对该入库单进行反审核操作?', '提示', {
			// 		confirmButtonText: '确定',
			// 		cancelButtonText: '取消',
			// 		type: 'warning'
			// 	}).then(() => {
			// 		this.axios({
			// 			url: "http://localhost:8089/eims/stockOut",
			// 			method: "put",
			// 			data: {
			// 				"stockOutId": val,
			// 				"audited": 0
			// 			}
			// 		}).then(res => {
			// 			this.show()
			// 		}).catch(err => {

			// 		})
			// 		this.$message({
			// 			type: 'success',
			// 			message: '成功反审核!'
			// 		});
			// 		this.show()
			// 	}).catch(() => {
			// 		this.$message({
			// 			type: 'info',
			// 			message: '已取消反审核操作'
			// 		});
			// 	});
			// },
			SelectFun(val) {
				this.multipleSelection = val
				if (this.multipleSelection.length <= 0) {
					this.delbut = false
				} else {
					this.delbut = true
				}
			},
			Pldelete() {
				
				for (var i = 0; i < this.multipleSelection.length; i++){
					if(this.multipleSelection[i].audited == 1){
						this.$message({
							type: 'info',
							message: '已审核的出库单不能进行删除操作!'
						})
						return false
					}
				}
						
				this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】张出库单, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					var Ids = []
					this.multipleSelection.forEach(item => {
						Ids.push(item.stockOutId);
					})
					this.axios({
						url: "http://localhost:8089/eims/stockOut/batch",
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
			handleSizeChange(val) {
				this.loding = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all')
					this.show()
				else if (this.queryType == 'search')
					this.SearachFor()
				else if (this.queryType == 'screen')
					this.ScreenFor()
			},
			handleCurrentChange(val) {
				this.loding = true
				this.queryForm.pageNum = val
				if (this.queryType == 'all')
					this.show()
				else if (this.queryType == 'search')
					this.SearachFor()
				else if (this.queryType == 'screen')
					this.ScreenFor()
			},
			ScreenFor() {
				this.queryType = 'screen'
			},

			SearachFor() {
				this.queryType = 'search'
				this.queryForm.stockOutDocunum = this.SearInput
				this.queryForm.warehouseName = this.SearInput
				this.queryForm.workPointName = this.SearInput
				this.queryForm.outboundType = this.SearInput

				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/stockOut/search',
					params: this.queryForm
				}).then(res => {
					console.log(res)
					this.queryForm.total = res.data.total
					this.tableData = res.data.list
				}).catch(err => {

				})
			},
			show() {
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/stockOut',
					params: {
						"pageNum": this.queryForm.pageNum,
						"pageSize": this.queryForm.pageSize,
						//"workPointId": this.queryForm.workPointId
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
	#Odo {
		/* background-color: aliceblue; */
	}

	#Odo .el-container {
		height: 100%;
		width: 100%;
	}

	#Odo .el-header,
	#Odo .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	#Odo .el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}

	#Odo .el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		height: 600px;

	}


	#Odo .el-row {
		margin-bottom: 5px;
	}

	#Odo .el-col {
		border-radius: 4px;
	}

	#Odo .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

	#Odo .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	/* .el-input__inner{
			width: 200px;
		} */
	#Odo .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}

	#Odo .el-dialog__body {
		padding: 5px;
	}

	#Odo .el-table td,
	#Odo .el-table th {
		padding: 6px 0;
	}
</style>
