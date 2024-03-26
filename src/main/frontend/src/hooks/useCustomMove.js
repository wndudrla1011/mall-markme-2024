import {
  createSearchParams,
  useNavigate,
  useSearchParams,
} from "react-router-dom";

const getNum = (param, defaultValue) => {
  if (!param) {
    return defaultValue;
  }
  return parseInt(param);
};

const useCustomMove = () => {
  const navigate = useNavigate();

  const [queryParams] = useSearchParams();

  const page = getNum(queryParams.get("page"), 1);
  const size = getNum(queryParams.get("size"), 10);

  const queryDefault = createSearchParams({ page, size }).toString();

  const moveToList = (pageParam) => {
    let queryStr = "";

    //요청 page, size 있음
    if (pageParam) {
      const pageNum = getNum(pageParam.page, page);
      const sizeNum = getNum(pageParam.size, size);

      queryStr = createSearchParams({
        page: pageNum,
        size: sizeNum,
      }).toString();
    }
    //요청 page, size 없음 => 기본값
    else {
      queryStr = queryDefault;
    }

    navigate({ pathname: "../list", search: queryStr });
  };

  return;
};

export default useCustomMove;
