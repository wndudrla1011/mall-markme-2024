import styles from "../css/common/loading.module.css";
import React from "react";

function Loading() {
  return (
    <div className={styles.main}>
      <div className={styles.droplet_spinner}>
        <div className={styles.droplet}></div>
        <div className={styles.droplet}></div>
        <div className={styles.droplet}></div>
      </div>
    </div>
  );
}

export default Loading;
