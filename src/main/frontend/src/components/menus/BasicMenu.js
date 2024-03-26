import React from "react";
import { Link } from "react-router-dom";
import styles from "../../css/basicMenu.module.css";

function BasicMenu() {
  return (
    <nav className={styles.navbar}>
      <div className={styles.navbar__logo}>
        <label className={styles.navbar__logo__back}>
          MARK
          <br />
          ME
        </label>
      </div>

      <ul className={styles.navbar__menu}>
        <li>
          <Link to={"/"}>HOME</Link>
        </li>
        <div className={styles.dropdown}>
          <button className={styles.dropdownBtn}>
            MY
            <li className={styles.dropdown__content}>
              <Link
                className={styles.dropdown__content__link}
                to={"/member/read/10"}
              >
                INFO
              </Link>
              <Link className={styles.dropdown__content__link} to={"/my/posts"}>
                MY-POSTS
              </Link>
              <Link
                className={styles.dropdown__content__link}
                to={"/my/comments"}
              >
                MY-COMMENTS
              </Link>
            </li>
          </button>
        </div>
        <li>
          <Link to={"/item"}>ITEMS</Link>
        </li>
        <li>
          <Link to={"/feedback"}>Q&A</Link>
        </li>
      </ul>

      <ul className={styles.navbar__login}>
        <li>
          <Link to={"/member/login"}>LOGIN</Link>
        </li>
      </ul>
    </nav>
  );
}

export default BasicMenu;
