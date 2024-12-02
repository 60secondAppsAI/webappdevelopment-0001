import axios from "axios"; 

export default axios.create({
  baseURL: "https://60secondApps-0010.vip:8080/",    //deployment-server 
//  baseURL: "https://${application.domain}:8080/",    //dedicated domain server
//  baseURL: "http://localhost:8090/",  //local
  headers: {
    "Content-type": "application/json",
  },
});


