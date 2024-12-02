<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <shippingBox-table
            v-if="shippingBoxs && shippingBoxs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:shippingBoxs="shippingBoxs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-shipping-boxs="getAllShippingBoxs"
             >

            </shippingBox-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ShippingBoxTable from "@/components/ShippingBoxTable";
import ShippingBoxService from "../services/ShippingBoxService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ShippingBoxTable,
  },
  data() {
    return {
      shippingBoxs: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllShippingBoxs(sortBy='shippingBoxId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ShippingBoxService.getAllShippingBoxs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.shippingBoxs.length) {
					this.shippingBoxs = response.data.shippingBoxs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching shippingBoxs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching shippingBox details:", error);
      }
    },
  },
  mounted() {
    this.getAllShippingBoxs();
  },
  created() {
    this.$root.$on('searchQueryForShippingBoxsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllShippingBoxs();
    })
  }
};
</script>
<style></style>
