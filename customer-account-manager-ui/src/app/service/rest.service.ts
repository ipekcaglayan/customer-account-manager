import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IAccount, ICustomer, ITransaction} from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private readonly transactionServiceUrl = "http://localhost:8081/api/transactions";
  private readonly accountServiceUrl = "http://localhost:8080/api";

  constructor(

    private readonly httpClient: HttpClient
  ) {
  }

  getCustomers(): Observable<ICustomer[]> {
    return this.httpClient.get<ICustomer[]>(this.accountServiceUrl + '/customers');
  }

  createCustomer(firstName: string, lastName: string): Observable<ICustomer> {
    return this.httpClient.post<ICustomer>(
      `${this.accountServiceUrl}/customers`,
      {
        firstName,
        lastName
      }
    );
  }

  createAccount(customerId: number, initialCredit: number): Observable<IAccount> {
    return this.httpClient.post<IAccount>(
      `${this.accountServiceUrl}/accounts`,
      {
        customerId,
        initialCredit
      }
    );
  }

  getCustomerAccounts(customerId: number): Observable<IAccount[]> {
    return this.httpClient.get<IAccount[]>(`${this.accountServiceUrl}/customers/${customerId}/accounts`);
  }

  createTransaction(accountId: number, amount: number): Observable<ITransaction> {
    return this.httpClient.post<ITransaction>(
      `${this.transactionServiceUrl}`,
      {
        accountId,
        amount
      }
    );
  }
  getAccountTransactions(accountId: number): Observable<ITransaction[]> {
    return this.httpClient.get<ITransaction[]>(`${this.transactionServiceUrl}/account/${accountId}`);
  }
}
