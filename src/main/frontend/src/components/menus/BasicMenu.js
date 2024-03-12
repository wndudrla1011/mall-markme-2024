import React from "react";
import { Link } from "react-router-dom";
import styles from "../../css/common/basicMenu.module.css";

function BasicMenu() {
  return (
    <nav className={styles.navbar}>
      <div>
        <img className="logo" alt="logo.ico" src="favicon.ico" />
      </div>

      <ul className={styles.navbar__menu}>
        <li>
          <Link to={"/"}>HOME</Link>
        </li>
        <li>
          <Link to={"/"}>MY</Link>
        </li>
        <li>
          <Link to={"/"}>ORDER</Link>
        </li>
        <li>
          <Link to={"/"}>CART</Link>
        </li>
        <li>
          <Link to={"/"}>Q&A</Link>
        </li>
      </ul>

      <ul className={styles.navbar__login}>
        <li>
          <Link>LOGIN</Link>
        </li>
      </ul>
    </nav>
  );
}

export default BasicMenu;
