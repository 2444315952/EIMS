<template>
	<el-row>
	<el-col :span="12">
		<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/PurchaseList">基础资料</a></el-breadcrumb-item>
			<el-breadcrumb-item><a href="/">商品管理</a></el-breadcrumb-item>
		</el-breadcrumb>
	</el-col>
	</el-row>
	
	<div style="border: 1px solid skyblue;background-color: #F1F1F1;height: 32px;">
		<b style="line-height: 32px;">&nbsp;商品列表</b>
		
	</div>
	<div style="border: 1px solid skyblue;height: 40px">
		&nbsp;
		<el-button icon="el-icon-circle-plus-outline" type="primary" @click="addshop=true,bianhao()" plain size="small" style="margin-top: 4px;margin-left: -20px;">添加商品</el-button>

		<el-button icon="el-icon-delete" type="primary" plain size="small" @click="deletes()">删除所选</el-button>

		<el-button icon="el-icon-circle-close" type="primary" plain size="small" @click="">禁用所选</el-button>
		&nbsp;
		<div style="float: right;margin-top: 4px;">

			<h>商品类别:</h>
			<el-select v-model="pageInfo.comm_type_name" placeholder="请选择" id="select1" size="small">
				<el-option v-for="item in jinyon" :key="item.commId" :label="item.commTypeName" :value="item.commTypeName">
				</el-option>
			</el-select>
			<h style="margin-left: 50px;">编号或者名称：</h>
			<div style="display: inline-block;margin-right: 10px;">
				<el-input type="text" v-model="pageInfo.product_name" placeholder="请输入内容" size="small"></el-input>
				{{pageInfo.product_name}}
			</div>


			<div style="display: inline-block;">
				{{pageInfo.is_enabled}}
				<el-select v-model="pageInfo.is_enabled" placeholder="请选择" id="select1" size="small">
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
	 @selection-change="handleSelectionChange" border @row-click="handle">
		<el-table-column type="selection" width="55" fixed>

		</el-table-column>
		<el-table-column label="商品编号" width="120" prop="productNumber">

		</el-table-column>
		<el-table-column prop="productName" label="商品名称" width="120">
		</el-table-column>

	<!-- 	<el-table-column prop="commodityType.commTypeName" label="类别" show-overflow-tooltip>
		</el-table-column> -->
		<el-table-column prop="productUnit" label="单位" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="specModel" label="规格型号" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="minimumStock" label="最低库存" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="marketPrice" label="预设售价" show-overflow-tooltip>
		</el-table-column>

		<el-table-column prop="referCost" label="进价" show-overflow-tooltip>
		</el-table-column>
		<el-table-column prop="remake" label="备注" show-overflow-tooltip>
		</el-table-column>


		<el-table-column prop="ck" label="操作" fixed="right">
			<template #default="scope">
				<el-button type="text" size="small" @click="setshop = true,upfind(scope.row)">编辑</el-button>
				<el-button @click="" type="text" size="small">禁用</el-button>
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
	<!-- 修改商品 -->
	<el-dialog title="修改商品" v-model="setshop">
		<el-form :model="Product1" label-width="80px" style="width: 1000px;">
			<el-row :span="3">
				<el-col :span="5">
					<el-form-item label="所属类别(必填)" :label-width="formLabelWidth">
						<el-select v-model="Product1.commId" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in jinyon" :key="item.commId" :label="item.commTypeName" :value="item.commId">
							</el-option>
						</el-select>
						<img src="../../assets/folder.gif" style="width: 30px;" @click="addshoptype=true" />
						<!-- ${{Product.commId}} -->
					</el-form-item>

					<el-form-item label="规格型号" :label-width="formLabelWidth">
						<el-input v-model="Product1.specModel" size="small" clearable></el-input>
					</el-form-item>
					
					<el-form-item label="参考成本价(必填)" :label-width="formLabelWidth">
						<el-input-number v-model="Product1.referCost" size="small" clearable></el-input-number>
					</el-form-item>
				</el-col>
				<el-col :span="5">
					<el-form-item label="商品编号(必填)" :label-width="formLabelWidth" disabled>
						<el-input v-model="Product1.productNumber" autocomplete="off " size="small"></el-input>
					</el-form-item>
					
					<el-form-item label="单位" :label-width="formLabelWidth">
						<el-input size="small" v-model="Product1.productUnit"></el-input>
					</el-form-item>
					<el-form-item label="最低库存" :label-width="formLabelWidth">
						<el-input-number size="small"></el-input-number>
					</el-form-item>
					<el-form-item label="市场价(必填)" :label-width="formLabelWidth">
						<el-input-number v-model="Product1.marketPrice" size="small" clearable></el-input-number>
					</el-form-item>

				</el-col>
				<el-col :span="5">
					<el-form-item label="商品名称(必填)" :label-width="formLabelWidth">
						<el-input v-model="Product1.productName" autocomplete="off" size="small"></el-input>
					</el-form-item>
					<el-form-item label="积分兑换" :label-width="formLabelWidth">
						<el-checkbox></el-checkbox>
					</el-form-item>

				
				</el-col>

			</el-row>
			<el-row :span="3">
				<el-col :span="5">

					<el-form-item label="备注" :label-width="formLabelWidth">
						<el-input v-model="Product.remake" size="small" type="textarea" style="width: 500px;"></el-input>
					</el-form-item>

				</el-col>
				<el-col :span="5">
					
					
				</el-col>
			</el-row>
			<el-col :span="5">
			
				<el-form-item label="商品图片" :label-width="formLabelWidth">

					<el-upload class="upload-demo" ref="upload" action="" :on-preview="handlePreview" :on-remove="handleRemove"
					 :file-list="fileList" :auto-upload="false" v-model="Product.productPicture">

						<el-button size="small" type="primary">选取文件</el-button>

					</el-upload>
				</el-form-item>
			</el-col>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="setshop = false;productallsel()">取 消</el-button>
				<el-button type="primary" @click="update()">确 定</el-button>
			</span>
		</template>
	</el-dialog>
	<!-- <-添加商品-> -->
	<el-dialog title="添加商品" v-model="addshop">
		<el-form :model="Product" label-width="80px" style="width: 1000px;">
			<el-row :span="3">
				<el-col :span="5">
					<el-form-item label="所属类别(必填)" :label-width="formLabelWidth">
						<el-select v-model="Product.commId" placeholder="请选择" id="select1" size="small">
							<el-option v-for="item in jinyon" :key="item.commId" :label="item.commTypeName" :value="item.commId">
							</el-option>
						

						</el-select>
						
						<img src="../../assets/image/folder.gif" style="width: 30px;" @click="addshoptype=true" />
					</el-form-item>

					<el-form-item label="规格型号" :label-width="formLabelWidth">
						<el-input v-model="Product.specModel" size="small" clearable></el-input>
					</el-form-item>
					
					<el-form-item label="参考成本价(必填)" :label-width="formLabelWidth">
						<el-input-number v-model="Product.referCost" size="small" clearable></el-input-number>
					</el-form-item>
				</el-col>
				<el-col :span="5">
					<el-form-item label="商品编号(必填)" :label-width="formLabelWidth">
						<el-input v-model="Product.productNumber" autocomplete="off " size="small"></el-input>
					</el-form-item>
					
					<el-form-item label="单位" :label-width="formLabelWidth">
						<el-input size="small" v-model="Product.productUnit"></el-input>
					</el-form-item>
					<el-form-item label="最低库存" :label-width="formLabelWidth">
						<el-input-number size="small" v-model="Product.minimumStock"></el-input-number>
					</el-form-item>
					<el-form-item label="市场价(必填)" :label-width="formLabelWidth">
						<el-input-number v-model="Product.marketPrice" size="small" clearable></el-input-number>
					</el-form-item>

				</el-col>
				<el-col :span="5">
					<el-form-item label="商品名称(必填)" :label-width="formLabelWidth">
						<el-input v-model="Product.productName" autocomplete="off" size="small"></el-input>
					</el-form-item>
					<el-form-item label="积分兑换" :label-width="formLabelWidth">
						<el-checkbox></el-checkbox>
					</el-form-item>

				
				</el-col>

			</el-row>
			<el-row :span="3">
				<el-col :span="5">

					<el-form-item label="备注" :label-width="formLabelWidth">
						<el-input v-model="Product.remake" size="small" type="textarea" style="width: 500px;"></el-input>
					</el-form-item>

				</el-col>
				<el-col :span="5">
					
					
				</el-col>
			</el-row>
			<!-- <el-col :span="5">
				
				<el-form-item label="商品图片" :label-width="formLabelWidth">

					<el-upload class="upload-demo" ref="upload" action="" :on-preview="handlePreview" :on-remove="handleRemove"
					 :file-list="fileList" :auto-upload="false" v-model="Product.productPicture">

						<el-button size="small" type="primary">选取文件</el-button>

					</el-upload>
				</el-form-item>
			</el-col> -->
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
	<!-- ${{Product}} -->



	<!-- <-商品类别添加-> -->
	<el-dialog title="商品类别添加" v-model="addshoptypeadd" width="30%">
		<div style="border: 1px solid skyblue;height: 140px">
			&nbsp;


			<el-form :model="CommodityType">

				<el-form-item label="" :label-width="formLabelWidth">
					上级类别: <el-select v-model="CommodityType.commTypeName1" placeholder="请选择" id="select1" size="small">
						<el-option v-for="item in shangji" :key="item.quanbu" :label="item.quanbu" :value="item.quanbu">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="" :label-width="formLabelWidth">
					类别名称:<el-input size="small" v-model="CommodityType.commTypeName" style="width: 180px;"></el-input>
				</el-form-item>


			</el-form>
			&nbsp;
		</div>



		<template #footer>

			<span class="dialog-footer">
				
				<el-button type="primary" @click="addshoptypeaddajax();addshoptypeadd = false">添 加</el-button>
				<el-button type="primary" @click="addshoptypeadd = false">返 回</el-button>

			</span>
		</template>
	</el-dialog>



	<!-- <-商品类别修改-> -->
	<el-dialog title="商品类别修改" v-model="addshoptypeupdate" width="30%">
		<div style="border: 1px solid skyblue;height: 140px">
			&nbsp;

			
			<el-form :model="CommodityType1">

				<el-form-item label="" :label-width="formLabelWidth">
					上级类别: <el-select v-model="CommodityType1.commTypeName1" placeholder="请选择" id="select1" size="small">
						<el-option v-for="item in shangji" :key="item.quanbu" :label="item.quanbu" :value="item.quanbu">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="" :label-width="formLabelWidth">
					类别名称:<el-input size="small" v-model="CommodityType1.commTypeName" style="width: 180px;"></el-input>
				</el-form-item>


			</el-form>
			&nbsp;
		</div>



		<template #footer>

			<span class="dialog-footer">
				<el-button type="primary" @click="addshoptypeupdate = false,updateshoptypeaddaxios()">添 加</el-button>
				<el-button type="primary" @click="addshoptypeupdate = false;productalltypesel()">返 回</el-button>
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
				form: {

					name: "",
					region: ""
				}, 
				Product: {
					productNumber: "",
					productName: "",
					productPicture: "",
					specModel: "",
					productUnit: "",
					referCost: "",
					marketPrice: "",
					commId: "",
					isEnabled: 0,
					remake: "",
					workPointId: "1",
					workPointName: "总店"
				},
				Product1: {
					productNumber: "",
					productName: "",
					productPicture: "",
					specModel: "",
					productUnit: "",
					referCost: "",
					marketPrice: "",
					commId: "",
					isEnabled: 0,
					remake: "",
					workPointId: "1",
					workPointName: "总店"
				},
				CommodityType: {
					commId: "",
					commTypeName: "",
					commTypeName1: ""
				},
				CommodityType1: {
					commId: "",
					commTypeName: "",
					commTypeName1: ""
				},
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
					product_name: "",
					comm_type_name: "",
					is_enabled: "",
					currentPage: 1,
					pageSize: 3,
					total: 0
				}
			}
		},

		methods: {
			qingkong() {
				this.Product= {
					productNumber: "",
					productName: "",
					productPicture: "",
					specModel: "",
					productUnit: "",
					referCost: "",
					marketPrice: "",
					commId: "",
					isEnabled: 0,
					remake: "",
					workPointId: "1",
					workPointName: "总店"
				}
			},
			bianhao() {
				var num = ""; //定义用户编号
				for (var i = 0; i < 4; i++) //4位随机数，用以加在时间戳后面。
				{
					num += Math.floor(Math.random() * 10);
				}
				num = new Date().getTime() + num; //时间戳，用来生成用户编号。
				console.log(num)
				this.Product.productNumber = num;
			},
			//编辑查看
			upfind(row) {
			
				console.log("修改" + row.productName)
				this.Product1 = row
			
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
						url: 'http://127.0.0.1:8089/eims/product/batch',
						data: aa
					}).then(function(response) {
					
						console.log(_this.multipleSelection)
						console.log(response.data)
						console.log(_this.multipleSelection)
						_this.productallsel()
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

			addshoptypeaddajax() {
				var _this = this
				this.axios.post("http://127.0.0.1:8089/eims/commodityType", this.CommodityType)
					.then(function(response) {
						
						console.log(response.data)

						_this.productalltypesel()
					}).catch(function(error) {
						console.log(error)
					});








			},


			updateshoptypeaddaxios() {
				var _this = this
				this.axios.put("http://127.0.0.1:8089/eims/commodityType", this.CommodityType1)
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
					_this.productalltypesel()
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
					console.log(val[aa].productId)
					this.multipleSelection[aa] = val[aa].productId;


				}


				console.log(this.multipleSelection)
			},
			guanjianzi() {
				var _this = this
				_this.axios.get("http://127.0.0.1:8089/eims/product/xyj", {
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
		

				if (this.Product.productNumber == "" || this.Product.productName == "" || this.Product.commId == "" || this.Product
					.is_enabled == "" || this.Product.workPointId == ""|| this.Product.referCost == ""|| this.Product.minimumStock == ""|| this.Product.marketPrice == "") {
					alert("必填未填完!")

				} else {
					var _this = this
					this.axios.post("http://127.0.0.1:8089/eims/product", _this.Product)
						.then(function(response) {
							
							console.log(response.data)
							_this.addshop = false

							_this.productallsel()
							_this.qingkong()
						}).catch(function(error) {
							console.log(error)
						})

				}




			},

			update() {
				if (this.Product1.productNumber == "" || this.Product1.productName == "" || this.Product1.commId == "" || this.Product1
					.is_enabled == "" || this.Product1.workPointId == ""|| this.Product.referCost == ""|| this.Product.minimumStock == ""|| this.Product.marketPrice == "") {
					alert("必填未填完!")
					
				} else {
					var _this = this
					this.axios.put("http://127.0.0.1:8089/eims/product", this.Product1)
						.then(function(response) {
						
							console.log(response.data)

							setshop = false
						}).catch(function(error) {
							console.log(error)
						})

				}

			},
			handleSizeChange(size) {
				var _this = this
				this.pageInfo.pageSize = size
				
				this.productallsel()

			},
			handleCurrentChange(page) {
				var _this = this
				this.pageInfo.currentPage = page
				
				this.productallsel()
			},
			productallsel() {
				var _this = this
				_this.axios.get("http://127.0.0.1:8089/eims/product/xyj", {
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
				
			},
			productalltypesel(){
				var _this = this
				_this.axios.get("http://127.0.0.1:8089/eims/commodityType").then(function(response) {
					console.log("商品类型：", response.data)
					_this.jinyon = response.data.list
					_this.jinyon.push({
						commTypeName:'所有',
						
						})
				
				
					console.log("-----"+_this.jinyon)
				})
				
			}
		},
		created() {
			this.productallsel()
			this.productalltypesel()
			
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
