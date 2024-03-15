import React from "react";
import { useParams } from "react-router-dom";

function ReadPage() {
  const { postId } = useParams();

  console.log(postId);

  return <div className="text-3xl">ReadPage</div>;
}

export default ReadPage;
