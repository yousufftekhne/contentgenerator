import axios from "axios";

const instance = axios.create({
  baseURL: process.env.REACT_APP_SERVICE_BASE_URL,
  headers: {
    "api-key": process.env.REACT_APP_SERVICE_ACCESS_KEY
  }
});

export default instance;
