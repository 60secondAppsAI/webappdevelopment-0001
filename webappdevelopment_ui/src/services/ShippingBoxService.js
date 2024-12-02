import http from "../http-common"; 

class ShippingBoxService {
  getAllShippingBoxs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/shippingBox/shippingBoxs`, searchDTO);
  }

  get(shippingBoxId) {
    return this.getRequest(`/shippingBox/${shippingBoxId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/shippingBox?field=${matchData}`, null);
  }

  addShippingBox(data) {
    return http.post("/shippingBox/addShippingBox", data);
  }

  update(data) {
  	return http.post("/shippingBox/updateShippingBox", data);
  }
  
  uploadImage(data,shippingBoxId) {
  	return http.postForm("/shippingBox/uploadImage/"+shippingBoxId, data);
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

export default new ShippingBoxService();
