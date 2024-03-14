import React from "react";
import { useParams } from "react-router-dom";

function ReadPage() {
  const { orderId } = useParams();

  console.log(orderId);

  return <div className="text-3xl">ReadPage</div>;
}

export default ReadPage;
