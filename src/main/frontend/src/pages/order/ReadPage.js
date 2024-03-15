import React from "react";
import { useParams, useSearchParams } from "react-router-dom";

function ReadPage() {
  const { orderId } = useParams();

  const [queryParams] = useSearchParams();

  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  return <div className="text-3xl">Read Page {orderId}</div>;
}

export default ReadPage;
