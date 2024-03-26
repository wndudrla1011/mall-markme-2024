import React from "react";
import BasicMenu from "../components/menus/BasicMenu";

function BasicLayout({ children }) {
  return (
    <>
      {/* 메뉴 */}
      <BasicMenu />

      {/* children */}
      <div className="bg-black w-full h-screen flex flex-col space-y-3">
        {children}
      </div>
    </>
  );
}

export default BasicLayout;
