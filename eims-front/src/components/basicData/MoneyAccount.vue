<template>
		<el-row>
	<el-col :span="12">
		<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/PurchaseList">基础资料</a></el-breadcrumb-item>
			<el-breadcrumb-item><a href="/">资金账户管理</a></el-breadcrumb-item>
		</el-breadcrumb>
	</el-col>
	</el-row>
	<div style="border: 1px solid skyblue;background-color: #F1F1F1;height: 32px;">
		<b style="line-height: 32px;">&nbsp;资金账户</b>
	</div>
	<div style="border: 1px solid skyblue;height: 40px">
		&nbsp;
		<el-button icon="el-icon-circle-plus-outline" type="primary" @click="addshop=true" plain size="small" style="margin-top: 4px;margin-left: -20px;">添加账户</el-button>

		<el-button icon="el-icon-delete" type="primary" plain size="small" @click="deletes()">删除所选</el-button>

		<el-button icon="el-icon-circle-close" type="primary" plain size="small" @click="jingyongup()">禁用所选</el-button>
		&nbsp;
		<div style="float: right;margin-top: 4px;">

			<h style="margin-left: 50px;">名称...：</h>
			<div style="display: inline-block;margin-right: 10px;">
				<el-input type="text" v-model="chaxun.accountName" placeholder="请输入内容" size="small"></el-input>
				
			</div>
			<div style="display: inline-block;margin-right: 8px;">
				<el-button @click="guanjianzi" size="small" icon="el-icon-search" circle></el-button>
			</div>

			<div style="display: inline-block;">
				
				<el-select v-model="chaxun.isEnabled" placeholder="请选择" id="select1" size="small" >
					<el-option v-for="item in scaticall" :key="item.value" :label="item.lable" :value="item.value" @click="guanjianzi">
					</el-option>
				</el-select>
			</div>
			
		</div>
	</div>

	<!-- 资金账户数据列表 -->

	<el-table ref="multipleTable" :data="moneyaccountend.tableData" tooltip-effect="dark" style="width: 100%;height: 500px;"
	 @selection-change="handleSelectionChange" border @row-click="handle" max-height="500px">
		<el-table-column type="selection" width="55" fixed>

		</el-table-column>
		<el-table-column label="账户名称" width="120" prop="accountName">

		</el-table-column>
		<el-table-column prop="workPointName" label="所属分店" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="accountBeginMoney" label="期初金额" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="accountMoney" label="金额" show-overflow-tooltip>
		</el-table-column>
		
		
		
		<el-table-column prop="isEnabled" label="禁用" show-overflow-tooltip>

			<template #default="scope">
				<span v-if="scope.row.isEnabled==0">
					<el-checkbox v-model="checkbox2" disabled></el-checkbox>
				</span>
				<span v-if="scope.row.isEnabled==1">
					<el-checkbox v-model="checkbox1" disabled></el-checkbox>
				</span>
				
			</template>

		</el-table-column>

		<el-table-column prop="remark" label="备注" show-overflow-tooltip>
		</el-table-column>



		<el-table-column prop="ck" label="操作" fixed="right">
			<template #default="scope">
				<el-button type="text" size="small" @click="upfind(scope.row),setshop = true">编辑</el-button>
				<el-button @click="" type="text" size="small" @click="jingyongopen(scope.row)">解禁</el-button>
			</template>
		</el-table-column>
	</el-table>

	<div style="border: 1px solid #EBEEF5; width: 100%; height: 50px;">

	</div>
	<div style="float: right;">
		<el-footer>
			<div class="block" style="float: right;">
				<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
					:page-sizes="[10,20,40,80]" :page-size="pageParam.pageSize"
					layout="total, sizes, prev, pager, next, jumper" :total="moneyaccountend.tableTotal">
				</el-pagination>
			</div>
		</el-footer>
	</div>
	<!-- 修改客户 -->
	<el-dialog title="修改客户" v-model="setshop">
		<el-form :model="moneyaccountsel" label-width="90px" style="width: 540px;">
			<el-form-item label="名称" :label-width="formLabelWidth">
				<el-input v-model="moneyaccountsel.accountName" size="small"></el-input>
			</el-form-item>
			<el-row span="2" :gutter="20">
				<el-col span="2">
					
					<el-form-item label="余额" :label-width="formLabelWidth">
						<el-input v-model="moneyaccountsel.accountMoney" size="small" type="number"></el-input>
					</el-form-item>
					<el-form-item label="所属分店" :label-width="formLabelWidth">
						<el-select v-model="moneyaccountsel.workPointName" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in fendian" :key="item.workPointName" :label="item.workPointName" :value="item.workPointName">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				
			</el-row>
			
			<el-form-item label="备注" :label-width="formLabelWidth">
				<el-input type="textarea" v-model="moneyaccountsel.remark" size="small"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="setshop = false,upview()">取 消</el-button>
				<el-button type="primary" @click="setshop = false,update()">确 定</el-button>
			</span>
		</template>

	</el-dialog>
	<!-- <-添加账户-> -->
	<el-dialog title="添加账户" v-model="addshop">
		<el-form :model="moneyaccountsel" label-width="90px" style="width: 540px;">
			<el-form-item label="名称" :label-width="formLabelWidth">
				<el-input v-model="moneyaccountsel.accountName" size="small"></el-input>
			</el-form-item>
			<el-row span="2" :gutter="20">
				<el-col span="2">
					
					<el-form-item label="期初金额" :label-width="formLabelWidth">
						<el-input v-model="moneyaccountsel.accountBeginMoney" size="small" type="number"></el-input>
					</el-form-item>
					<el-form-item label="所属分店" :label-width="formLabelWidth">
						<el-select v-model="moneyaccountsel.workPointName" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in fendian" :key="item.workPointName" :label="item.workPointName" :value="item.workPointName">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				
			</el-row>
			
			<el-form-item label="备注" :label-width="formLabelWidth">
				<el-input type="textarea" v-model="moneyaccountsel.remark" size="small"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="addshop = false">取 消</el-button>
				<el-button type="primary" @click="addoneone()">确 定</el-button>
			</span>
		</template>
	</el-dialog>





	



</template>

<script>
	export default {
		methods: {
			handleClick(row) {
				console.log(row);
			}
		},
		data() {
			return {
				moneyaccountend: {
					dialogVisible: false,
					searchInput: '',
					tableData: [],
					tableTotal: '',
					multipleSelection: [],
					pageParam: {
						"pageNum": 1,
						"pageSize": 10
					},
					sourceRowIndex: 0
				},
				pageParam: {
					"pageNum": 1,
					"pageSize": 10
				},
				moneyaccountsel:{},

				scaticall: [{
					value: '',
					lable: "全部"
				}, {
					value: 0,
					lable: "正常"
				}, {
					value: 1,
					lable: "禁用"
				}],
				jinyon: [],
				tableData: [],
				tableTotal: 0,

				multipleSelection: [],
				multipleSelectionAll: [],
				dialogFormVisible: false,
				addshop: false,
				addshoptype: false,
				addshoptypeadd: false,
				addshoptypeupdate: false,
				setshop: false,
				checked: false,
				checkbox1: true,
				checkbox2: false,
				
				
				fendian: [{
						workPointId: "1",
						workPointName: "示例工作点"
					},
					

				],
				
				shangji: [{
					quanbu: "全部"
				}],
				chaxun:{
				"accountName":'',
				"isEnabled":'',
				},
				queryType: 'all'
			
				
			}
		},

		methods: {
			
			//编辑查看
			upfind(row) {
				// var jsonData = JSON.stringify(index)
				// var jsonData1 =index
				// console.log("修改" + row.productName)
				this.moneyaccountsel = row
				//console.log("修改1"+jsonData1)

				// console.log(this.jinyon)
				// console.log(this.jinyon.array)

			},
			deletes() {

				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					var _this = this;
					var aa = _this.multipleSelection



					_this.axios({
						method: 'delete',
						url: 'http://127.0.0.1:8089/eims/moneyAccount/batch',
						data: aa
					}).then(function(response) {
						// this.addshop = false
						console.log(_this.multipleSelection)
						console.log(response.data)
						console.log(_this.multipleSelection)
						_this.multipleSelection=[]
						_this.upview()

					}).catch(function(error) {
						console.log(error)
					})




				}).catch(() => {
					
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});





			

			},


			jingyongup() {

				this.$confirm('此操作将禁用该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.multipleSelectionAll.forEach(aa=>{
						aa.isEnabled=1
					})
					var _this = this
					this.axios.put("http://127.0.0.1:8089/eims/moneyAccount/batch", this.multipleSelectionAll)
						.then(function(response) {
							
							console.log(response.data)
							_this.upview()
							this.$message({
								type: 'warning',
								message: '禁用修改成功'
							})
						}).catch(function(error) {
							console.log(error)
						})
						this.multipleSelectionAll=[]
					

					




				}).catch(() => {
					
					this.$message({
						type: 'info',
						message: '已取消禁用'
					});
				});





			},


			jingyongopen(index) {
				
				var moneyaccountone=index
				
				this.$confirm('此操作将解禁该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					moneyaccountone.isEnabled=0
					var _this = this
					this.axios.put("http://127.0.0.1:8089/eims/moneyAccount", moneyaccountone)
						.then(function(response) {
							
							console.log(response.data)
							_this.upview()
							this.$message({
								type: 'warning',
								message: '解禁修改成功'
							})
					
					

					}).catch(function(error) {
						console.log(error)
					})




				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消解禁'
					});
				});





			},

			addshoptypeaddajax() {
				this.axios.post("http://127.0.0.1:8089/eims/commodityType", this.CommodityType)
					.then(function(response) {
						// this.addshop = false
						console.log(response.data)

						window.location.reload()
					}).catch(function(error) {
						console.log(error)
					});







			


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
				this.multipleSelection = [];
				this.multipleSelectionAll = val;
				
				for (var aa in val) {
					console.log(val[aa].moneyAccountId)
					this.multipleSelection[aa] = val[aa].moneyAccountId;


				}


				console.log(this.multipleSelection)
			},
			

			addoneone() {



				if (this.moneyaccountsel.accountName == "" || this.moneyaccountsel.accountBeginMoney == "" || this.moneyaccountsel.workPointName == "") {
					alert("必填未填完!")

				} else {
					console.log(this.moneyaccountsel)
					this.moneyaccountsel.accountMoney=this.moneyaccountsel.accountBeginMoney
					var _this = this
					this.axios.post("http://127.0.0.1:8089/eims/moneyAccount", this.moneyaccountsel)
						.then(function(response) {
							
							console.log(response.data)
							_this.upview()
							this.$message({
								type: 'warning',
								message: '资金账户添加成功'
							})
						}).catch(function(error) {
							console.log(error)
						})
						this.moneyaccountsel={}
					this.addshop = false

				}




			},

			update() {
				if (this.moneyaccountsel.accountName == "" || this.moneyaccountsel.accountMoney == "" || this.moneyaccountsel.workPointName == "") {
					alert("必填未填完!")

				} else {
					var _this = this
					this.axios.put("http://127.0.0.1:8089/eims/moneyAccount", this.moneyaccountsel)
						.then(function(response) {
							
							console.log(response.data)
							_this.upview()
							this.$message({
								type: 'warning',
								message: '资金账户修改成功'
							})
						}).catch(function(error) {
							console.log(error)
						})
						this.moneyaccountsel={}
					this.addshop = false

				}

			},
			handleSizeChange(val) {
				this.pageParam.pageSize = val
			
				if (this.queryType == 'all')
					this.upview()
				else if (this.queryType == 'search')
					this.guanjianzi()
				else if (this.queryType == 'screen')
					this.guanjianzi()
			},
			handleCurrentChange(val) {
				this.pageParam.pageNum = val
			
				if (this.queryType == 'all')
					this.upview()
				else if (this.queryType == 'search')
					this.guanjianzi()
				else if (this.queryType == 'screen')
					this.guanjianzi()
			},
			guanjianzi() {
				var searchForm = Object.assign(this.chaxun,this.pageParam)
				console.log(searchForm)
				this.axios({
						url: "http://localhost:8089/eims/moneyAccount/search",
						method: 'get',
						params: searchForm
					}).then((response) => {
						this.moneyaccountend.tableData = response.data.list
						this.moneyaccountend.tableTotal = response.data.total
						this.queryType='search'
					}).catch((error) => {
				
					})
			
			},
			
			upview() {
				this.axios({
						url: "http://localhost:8089/eims/moneyAccount/search",
						method: 'get',
						params: this.pageParam
					}).then((response) => {
						this.moneyaccountend.tableData = response.data.list
						this.moneyaccountend.tableTotal = response.data.total
						this.queryType='all'
					}).catch((error) => {
				
					})
				
			},
			
		},
		created() {
			this.upview()

		}


	}
</script>


<style>
	#aaa {
		width: 150px;
		margin-left: 10px;
	}

	#select1 {
		width: 100px;

	}

	#select2 {
		width: 50px;

	}

	h {
		font-size: 12px;
	}

	#input_1 {
		margin-left: -130px;
	}
</style>
