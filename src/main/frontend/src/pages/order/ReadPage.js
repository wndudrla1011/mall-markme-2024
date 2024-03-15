import React from "react";
import {
  createSearchParams,
  useNavigate,
  useParams,
  useSearchParams,
} from "react-router-dom";

function ReadPage() {
  const { orderId } = useParams();
  const navigate = useNavigate();

  const [queryParams] = useSearchParams();

  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  const queryStr = createSearchParams({ page, size });

  const moveToEdit = (orderId) => {
    navigate({
      pathname: `/order/edit/${orderId}`,
      search: queryStr,
    });
  };

  return (
    <div className="text-3xl">
      Read Page {orderId}
      <div>
        <button onClick={() => moveToEdit(orderId)}>수정</button>
      </div>
    </div>
  );
}

export default ReadPage;
