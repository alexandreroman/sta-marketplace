/*
 * Copyright (c) 2023 VMware, Inc. or its affiliates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vmware.tanzu.demos.sta.marketplace.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public interface UserStockTransactionRepository extends JpaRepository<UserStockTransaction, String> {
    List<UserStockTransaction> findByUserOrderByTransactionTime(String user);

    @Query("select sum(tx.shares) from UserStockTransaction tx where tx.stockSymbol = :symbol and tx.transactionTime >=:start and tx.transactionTime <= :end")
    BigDecimal sumStockTransactionsInTimeRange(String symbol, ZonedDateTime start, ZonedDateTime end);
}
