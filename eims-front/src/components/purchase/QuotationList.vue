<template>

	<div id="QuotationList">

		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right" style="padding-bottom: 16px">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item><a href="/QuotationList">报价单列表</a></el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>

		<el-container style="background-color: white;padding-top: 15px;">

			<el-header style="height: 30px;">
				<el-row>

					<el-col :span="12">
						<el-input style="width: 290px;float: left;" placeholder="搜索单据编号" v-model="input">
							<template #append>
								<el-button icon="el-icon-search" size="small"></el-button>
							</template>
						</el-input>

					</el-col>

				</el-row>

			</el-header>

			<el-main style="background-color: white;">

				<el-table :data="tableData" style="width: 100%;height:487px;" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column label="单据编号" prop="empno">
					</el-table-column>
					<el-table-column label="单据日期" prop="empno">
					</el-table-column>
					<el-table-column label="询价发起者" prop="empno">
					</el-table-column>
					<el-table-column label="询价接受者" prop="empno">
					</el-table-column>
					<el-table-column label="报价状态" prop="ename">
					</el-table-column>
				</el-table>

			</el-main>
			<el-footer>
				<div class="block" style="float: right;">
					<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
						:page-sizes="[10,20,40,80]" :page-size="pageInfo.pageSize"
						layout="total, sizes, prev, pager, next, jumper" :total="pageInfo.total">
					</el-pagination>
				</div>
			</el-footer>
		</el-container>

	</div>

</template>

<script>
	import qs from 'qs'

	export default {
		name: "InquiryList",
		data() {
			return {
				tableData: [],
				pageInfo: {
					pageNum: 1,
					pageSize: 10,
					total: ''
				},
				multipleSelection: []
			}
		},
		methods: {
			toDetail() {
				this.$router.push({
					name: 'Inquiry'
				})
			},
			handleSelectionChange() {

			}
		}
	}
</script>