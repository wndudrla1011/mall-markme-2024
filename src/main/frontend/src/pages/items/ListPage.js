import React, { useCallback } from "react";
import ListComponent from "../../components/items/ListComponent";
import { useNavigate } from "react-router-dom";

function ListPage() {
  const navigate = useNavigate();

  const handleClickAdd = useCallback(() => {
    navigate({ pathname: "add" });
  });

  return (
    <div>
      <ListComponent />
    </div>
  );
}

export default ListPage;
