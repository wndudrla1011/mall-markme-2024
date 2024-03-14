import React from "react";
import { useSearchParams } from "react-router-dom";

function ListPage() {
  const [queryParams] = useSearchParams();

  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  return (
    <div className="text-3xl">
      List Page -- {page} -- {size}
    </div>
  );
}

export default ListPage;
