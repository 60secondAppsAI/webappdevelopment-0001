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
            <website-table
            v-if="websites && websites.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:websites="websites"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-websites="getAllWebsites"
             >

            </website-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import WebsiteTable from "@/components/WebsiteTable";
import WebsiteService from "../services/WebsiteService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    WebsiteTable,
  },
  data() {
    return {
      websites: [],
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
    async getAllWebsites(sortBy='websiteId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await WebsiteService.getAllWebsites(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.websites.length) {
					this.websites = response.data.websites;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching websites:", error);
        }
        
      } catch (error) {
        console.error("Error fetching website details:", error);
      }
    },
  },
  mounted() {
    this.getAllWebsites();
  },
  created() {
    this.$root.$on('searchQueryForWebsitesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllWebsites();
    })
  }
};
</script>
<style></style>
