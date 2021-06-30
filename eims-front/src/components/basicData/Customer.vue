<template>
		<el-row>
	<el-col :span="12">
		<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/PurchaseList">基础资料</a></el-breadcrumb-item>
			<el-breadcrumb-item><a href="/">客户管理</a></el-breadcrumb-item>
		</el-breadcrumb>
	</el-col>
	</el-row>
	<div style="border: 1px solid skyblue;background-color: #F1F1F1;height: 32px;">
		<b style="line-height: 32px;">&nbsp;客户</b>
	</div>
	<div style="border: 1px solid skyblue;height: 40px">
		&nbsp;
		<el-button icon="el-icon-circle-plus-outline" type="primary" @click="addshop=true" plain size="small" style="margin-top: 4px;margin-left: -20px;">添加客户</el-button>

		<el-button icon="el-icon-delete" type="primary" plain size="small" @click="deletes()">删除所选</el-button>

		<el-button icon="el-icon-circle-close" type="primary" plain size="small" @click="jingyongup()">禁用所选</el-button>
		&nbsp;
		<div style="float: right;margin-top: 4px;">

			<h style="margin-left: 50px;">名称...：</h>
			<div style="display: inline-block;margin-right: 10px;">
				<el-input type="text" v-model="pageInfo.customerName" placeholder="请输入内容" size="small"></el-input>
				{{pageInfo.product_name}}
			</div>


			<div style="display: inline-block;">
				{{pageInfo.isEnabled}}
				<el-select v-model="pageInfo.isEnabled" placeholder="请选择" id="select1" size="small">
					<el-option v-for="item in scaticall" :key="item.value" :label="item.lable" :value="item.lable">
					</el-option>
				</el-select>
			</div>
			<div style="display: inline-block;margin-right: 8px;">
				<el-button @click="guanjianzi" size="small" icon="el-icon-search" circle></el-button>
			</div>
		</div>
	</div>

	<!-- 商品数据列表 -->

	<el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%;height: 500px;"
	 @selection-change="handleSelectionChange" border @row-click="handle" max-height="500px">
		<el-table-column type="selection" width="55" fixed>

		</el-table-column>
		<el-table-column label="客户名称" width="120" prop="customerName">

		</el-table-column>
		<el-table-column prop="contact" label="联系人" width="120">
		</el-table-column>

		<el-table-column prop="contactNumber" label="联系电话" width="120">
		</el-table-column>

		<el-table-column prop="customerType" label="类别" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="" label="金额" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="workPointName" label="所属分店" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="contactAddress" label="地址" show-overflow-tooltip>
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
				<el-button type="text" size="small" @click="setshop = true,upfind(scope.row)">编辑</el-button>
				<el-button @click="" type="text" size="small" @click="jingyongopen(scope.row)">解禁</el-button>
			</template>
		</el-table-column>
	</el-table>

	<div style="border: 1px solid #EBEEF5; width: 100%; height: 50px;">

	</div>
	<div style="float: right;">
		<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageInfo.currentPage"
		 :page-sizes="[2, 3, 6, 10]" :page-size="pageInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="pageInfo.total">
		</el-pagination>
	</div>
	<!-- 修改客户 -->
	<el-dialog title="修改客户" v-model="setshop">
		<el-form :model="Customer" label-width="90px" style="width: 540px;">
			<el-form-item label="名称" :label-width="formLabelWidth">
				<el-input v-model="Customer1.customerName" size="small"></el-input>
			</el-form-item>
			<el-row span="2" :gutter="20">
				<el-col span="2">
					<el-form-item label="类型" :label-width="formLabelWidth">
						<el-select v-model="Customer1.customerType" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in jinyon" :key="item.commId" :label="item.commTypeName" :value="item.commId">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="联系人" :label-width="formLabelWidth">
						<el-input v-model="Customer1.contact" size="small"></el-input>
					</el-form-item>
					<el-form-item label="联系电话" :label-width="formLabelWidth">
						<el-input v-model="Customer1.contactNumber" size="small"></el-input>
					</el-form-item>
					<el-form-item label="期初应付" :label-width="formLabelWidth">
						<el-input v-model="form.region" size="small"></el-input>
					</el-form-item>
					<el-form-item label="所属分店" :label-width="formLabelWidth">
						<el-select v-model="Customer1.workPointName" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in fendian" :key="item.workPointName" :label="item.workPointName" :value="item.workPointName">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col span="3">
					<el-form-item label="所属地区" :label-width="formLabelWidth">
					
						<el-input v-model="Customer1.contactAddress   " size="small"></el-input>
					</el-form-item>
					
					</el-form-item>
				
						<el-checkbox v-model="Customer1.isEnabled"></el-checkbox>
					</el-form-item>
				</el-col>
			</el-row>
			
			<el-form-item label="备注" :label-width="formLabelWidth">
				<el-input type="textarea" v-model="Customer1.remark" size="small"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="setshop = false;upview()">取 消</el-button>
				<el-button type="primary" @click="setshop = false,update()">确 定</el-button>
			</span>
		</template>

	</el-dialog>
	<!-- <-添加客户-> -->
	<el-dialog title="添加客户" v-model="addshop">
		<el-form :model="Customer" label-width="90px" style="width: 540px;">
			<el-form-item label="名称(必填)" :label-width="formLabelWidth">
				<el-input v-model="Customer.customerName" size="small"></el-input>
			</el-form-item>
			<el-row span="2" :gutter="20">
				<el-col span="2">
					<el-form-item label="类型(必填)" :label-width="formLabelWidth">
						<el-select v-model="Customer.customerType" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in jinyon" :key="item.commId" :label="item.commTypeName" :value="item.commId">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="联系人(必填)" :label-width="formLabelWidth">
						<el-input v-model="Customer.contact" size="small"></el-input>
					</el-form-item>
					<el-form-item label="联系电话(必填)" :label-width="formLabelWidth">
						<el-input v-model="Customer.contactNumber" size="small"></el-input>
					</el-form-item>
					<el-form-item label="期初应付" :label-width="formLabelWidth">
						<el-input v-model="form.region" size="small"></el-input>
					</el-form-item>
					<el-form-item label="所属分店" :label-width="formLabelWidth">
						<el-select v-model="Customer.workPointName" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in fendian" :key="item.workPointName" :label="item.workPointName" :value="item.workPointName">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col span="3">
					<el-form-item label="所属地区(必填)" :label-width="formLabelWidth">
						
						<el-input v-model="Customer.contactAddress   " size="small"></el-input>
					</el-form-item>
					</el-select>
					</el-form-item>
				
					<el-form-item label="禁用" :label-width="formLabelWidth">
						
						<el-checkbox v-model="Customer.isEnabled"></el-checkbox>

						
					</el-form-item>
				</el-col>
			</el-row>
			
			<el-form-item label="备注" :label-width="formLabelWidth">
				<el-input type="textarea" v-model="Customer.remark" size="small"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="addshop = false">取 消</el-button>
				<el-button type="primary" @click="addoneone()">确 定</el-button>
			</span>
		</template>
	</el-dialog>





	<!-- <-商品类别-> -->
	<el-dialog title="商品类别" v-model="addshoptype" width="30%">
		<div style="border: 1px solid skyblue;height: 40px">
			&nbsp;
			<el-button icon="el-icon-circle-plus-outline" type="primary" @click="addshoptypeadd=true" plain size="small" style="margin-top: 4px;margin-left: -20px;">添加</el-button>

			
			&nbsp;
		</div>
		<el-table ref="multipleTable" max-height="300" :data="jinyon" style="width: 100%;height: 300px;" border @row-click="handle">



			<el-table-column prop="commTypeName" label="类别">
			</el-table-column>




			<el-table-column prop="ck" label="操作">
				<template #default="scope">
					<el-button type="text" size="small" @click="addshoptypeupdate=true,CommodityType1=scope.row">编辑</el-button>
					<el-button @click="" type="text" @click="delshoptypeaddaxios(scope.row.commId)">删除</el-button>
				</template>
			</el-table-column>

		</el-table>


		<template #footer>

			<span class="dialog-footer">
			
				<el-button type="primary" @click="addshoptype = false">返 回</el-button>
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

				scaticall: [{
					value: 0,
					lable: "全部"
				}, {
					value: 1,
					lable: "正常"
				}, {
					value: 2,
					lable: "禁用"
				}],
				jinyon: [],
				tableData: [],

				multipleSelection: [],
				dialogFormVisible: false,
				addshop: false,
				addshoptype: false,
				addshoptypeadd: false,
				addshoptypeupdate: false,
				setshop: false,
				checked: false,
				checkbox1: true,
				checkbox2: false,
				form: {

					name: "",
					region: ""
				},
				Customer: {
					customerId: "",
					customerName: "",
					customerType: "",
					customerLevel: "",
					contact: "",
					contactAddress: "",
					contactNumber: "",
					contactEmaill: "",
					isEnabled: 0,
					remark: "",
					workPointId: "1",
					workPointName: "总店"
				},
				Customer1: {
					customerId: "",
					customerName: "",
					customerType: "",
					customerLevel: "",
					contact: "",
					contactAddress: "",
					contactNumber: "",
					contactEmaill: "",
					isEnabled: 0,
					remark: "",
					workPointId: "1",
					workPointName: "总店"
				},
				fendian: [{
						workPointId: "1",
						workPointName: "总店"
					},
					{
						workPointId: "2",
						workPointName: "分店"
					}

				],
				// CommodityType: {
				// 	commId:"",
				// 	commTypeName:"",
				// 	commTypeName1:""
				// },
				// CommodityType1: {
				// 	commId:"",
				// 	commTypeName:"",
				// 	commTypeName1:""
				// },
				shangji: [{
					quanbu: "全部"
				}],
				shortcuts: [{
					text: '最近一周',
					value: (() => {
						const end = new Date()
						const start = new Date()
						start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
						return [start, end]
					})(),
				}, {
					text: '最近一个月',
					value: (() => {
						const end = new Date()
						const start = new Date()
						start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
						return [start, end]
					})(),
				}, {
					text: '最近三个月',
					value: (() => {
						const end = new Date()
						const start = new Date()
						start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
						return [start, end]
					})(),
				}],
				value1: '',
				value2: '',
				pageInfo: {
					customerName: "",
					workPointId: 0,
					isEnabled: "",
					currentPage: 1,
					pageSize: 3,
					total: 0
				}
			}
		},

		methods: {
			
			//编辑查看
			upfind(row) {
				// var jsonData = JSON.stringify(index)
				// var jsonData1 =index
				// console.log("修改" + row.productName)
				this.Customer1 = row
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
						url: 'http://127.0.0.1:8089/eims/customer/batch',
						data: aa
					}).then(function(response) {
						// this.addshop = false
						console.log(_this.multipleSelection)
						console.log(response.data)
						console.log(_this.multipleSelection)
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





				// this.axios.post("http://127.0.0.1:8088/jieyeplus/product/deleteall", this.multipleSelection,contentType="utf-8  ")
				// 	.then(function(response) {
				// 		// this.addshop = false
				// 		console.log(response.data)

				// 		// window.location.reload()
				// 	}).catch(function(error) {
				// 		console.log(error)
				// 	})

			},


			jingyongup() {

				this.$confirm('此操作将禁用该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					var _this = this;
					var aa = _this.multipleSelection

					var aanumber = 1

					_this.axios({
						method: 'delete',
						url: 'http://127.0.0.1:8089/eims/stopcustomer',
						data: aa


					}).then(function(response) {
						// this.addshop = false
						console.log(_this.multipleSelection)
						console.log(response.data)
						console.log(_this.multipleSelection)
						_this.upview()

					}).catch(function(error) {
						console.log(error)
					})




				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消禁用'
					});
				});





			},


			jingyongopen(index) {
				var aa = [];
				aa.push(index.customerId);
				console.log(aa)

				this.$confirm('此操作将解禁该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					var _this = this;


					var aanumber = 1

					_this.axios({
						method: 'delete',
						url: 'http://127.0.0.1:8089/eims/opendateallopen',
						data: aa


					}).then(function(response) {
						// this.addshop = false
						console.log(_this.multipleSelection)
						console.log(response.data)
						console.log(_this.multipleSelection)
						_this.upview()

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


			updateshoptypeaddaxios() {
				this.axios.put("http://127.0.0.1:8089/eims/commodityType/updateOne", this.CommodityType1)
					.then(function(response) {
					
						console.log(response.data)

					}).catch(function(error) {
						console.log(error)
					});




			},

			delshoptypeaddaxios(aa) {
			
				var _this = this

				_this.axios({
					method: 'delete',
					url: 'http://127.0.0.1:8089/eims/commodityType',
					params: {
						"id": aa
					}

				}).then(function(response) {
				
					console.log(_this.multipleSelection)
					console.log(response.data)
					console.log(_this.multipleSelection)
					window.location.reload()
				}).catch(function(error) {
					console.log(error)
				})



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
				for (var aa in val) {
					console.log(val[aa].customerId)
					this.multipleSelection[aa] = val[aa].customerId;


				}


				console.log(this.multipleSelection)
			},
			guanjianzi() {
				var _this = this
				_this.axios.get("http://127.0.0.1:8089/eims/customer/xyj", {
						params: _this.pageInfo
					})
					.then(function(response) {
						console.log(response.data)
						_this.tableData = response.data.list
						console.log("++++++", response.data.list)
						_this.pageInfo.total = response.data.total
					}).catch(function(error) {
						console.log(error)
					})

			},


			addoneone() {



				if (this.Customer.customerName == "" || this.Customer.contact == "" || this.Customer.contactAddress == "" || this.Customer
					.contactNumber == "") {
					alert("必填未填完!")

				} else {
					console.log(this.Customer)
					var _this = this
					this.axios.post("http://127.0.0.1:8089/eims/customer", this.Customer)
						.then(function(response) {
							
							console.log(response.data)
							_this.upview()
							
						}).catch(function(error) {
							console.log(error)
						})
					this.addshop = false

				}




			},

			update() {
				if (this.Customer1.customerName == "" || this.Customer1.contact == "" || this.Customer1.contactAddress == "" ||
					this.Customer1
					.contactNumber == "") {
					alert("必填未填完!")

				} else {
					var _this = this
					this.axios.put("http://127.0.0.1:8089/eims/customer", this.Customer1)
						.then(function(response) {
						
							console.log(response.data)
							_this.upview()
							
						}).catch(function(error) {
							console.log(error)
						})
					this.addshop = false

				}

			},
			handleSizeChange(size) {
				var _this = this
				this.pageInfo.pageSize = size
				
				this.axios.get("http://127.0.0.1:8089/eims/customer/xyj", {
						params: this.pageInfo
					})
					.then(function(response) {
						console.log("-------------------------------------------")
						console.log(response.data)
						_this.tableData = response.data.list
						_this.pageInfo.total = response.data.total
					}).catch(function(error) {
						console.log(error)
					})

			},
			handleCurrentChange(page) {
				var _this = this
				this.pageInfo.currentPage = page
			
				this.axios.get("http://127.0.0.1:8089/eims/customer/xyj", {
						params: this.pageInfo
					})
					.then(function(response) {
						console.log("-------------------page------------------------")
						console.log(response.data)
						_this.tableData = response.data.list

					}).catch(function(error) {
						console.log(error)
					})
			},
			upview() {
				var _this = this
				_this.axios.get("http://127.0.0.1:8089/eims/customer/xyj", {
						params: _this.pageInfo
					})
					.then(function(response) {
						console.log(response.data)
						_this.tableData = response.data.list
						console.log("++++++", response.data.list)
						_this.pageInfo.total = response.data.total
					
					}).catch(function(error) {
						console.log(error)
					});

			}
		},
		created() {
			var _this = this
			_this.axios.get("http://127.0.0.1:8089/eims/customer/xyj", {
					params: _this.pageInfo
				})
				.then(function(response) {
					console.log(response.data)
					_this.tableData = response.data.list
					console.log("++++++", response.data.list)
					_this.pageInfo.total = response.data.total
				
				}).catch(function(error) {
					console.log(error)
				});

			_this.axios.get("http://127.0.0.1:8089/eims/commodityType").then(function(response) {
				console.log("商品类型：", response.data)
				_this.jinyon = response.data.list


				console.log(_this.jinyon)
			})

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
