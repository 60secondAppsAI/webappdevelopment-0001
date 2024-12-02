import http from "../http-common"; 

class WebsiteService {
  getAllWebsites(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/website/websites`, searchDTO);
  }

  get(websiteId) {
    return this.getRequest(`/website/${websiteId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/website?field=${matchData}`, null);
  }

  addWebsite(data) {
    return http.post("/website/addWebsite", data);
  }

  update(data) {
  	return http.post("/website/updateWebsite", data);
  }
  
  uploadImage(data,websiteId) {
  	return http.postForm("/website/uploadImage/"+websiteId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new WebsiteService();
