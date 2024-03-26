import axios from "axios";
import { API_SERVER_HOST } from "./../App";

const host = `${API_SERVER_HOST}/api/items`;

export const getList = async (pageParam) => {
  const { page, size } = pageParam;

  const res = await axios.get(`${host}/list`, {
    params: { page: page, size: size },
  });

  return res.data;
};
