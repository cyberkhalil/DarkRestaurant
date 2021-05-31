package com.example.products.model;

import androidx.annotation.NonNull;

public enum PayType {
    CASH {
        @NonNull
        @Override
        public String toString() {
            return "كاش";
        }
    }, TAKSEET {
        @NonNull
        @Override
        public String toString() {
            return "تقسيط";
        }
    }, BOTH {
        @NonNull
        @Override
        public String toString() {
            return "كاش + تقسيط";
        }
    }
}
