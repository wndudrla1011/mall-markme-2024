import React from "react";
import BasicMenu from "../components/menus/BasicMenu";

function BasicLayout({ children }) {
  return (
    <>
      {/* 메뉴 */}
      <BasicMenu />

      {/* List */}
      <div>{children}</div>
    </>
  );
}

export default BasicLayout;
