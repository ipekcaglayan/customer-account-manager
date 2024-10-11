import {Component, OnInit, signal} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {MatIcon} from '@angular/material/icon';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {NgForOf} from '@angular/common';
import {IAccount, ICustomer, ITransaction} from './model/models';
import {RestService} from './service/rest.service';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatDivider} from '@angular/material/divider';
import {
  MatExpansionModule,

} from '@angular/material/expansion';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, HttpClientModule, MatExpansionModule, MatIcon, MatMenu, MatMenuItem, NgForOf, MatMenuTrigger, MatIconButton, MatDivider, MatTableModule, MatButton],
  providers: [RestService]
})
export class AppComponent implements OnInit {
  customers: ICustomer[] = [];
  selectedCustomer: ICustomer | null = null;
  selectedAccount: IAccount | null = null
  accounts: IAccount[] = []
  transactions: ITransaction[] = [];
  displayedColumns: string[] = ['amount', 'date'];
  initialBalance: number = 0;
  readonly panelOpenState = signal(false);

  constructor(
    private _service: RestService
  ) {

  }

  ngOnInit(): void {
    this._service.getCustomers().subscribe({
      next: response => {
        this.customers = response
        if (response.length > 0) {
          this.selectedCustomer = response[0];
        }
        this.getAccounts()
      }
    });
  }

  getAccounts() {
    this._service.getCustomerAccounts(this.selectedCustomer?.id || -1).subscribe({
      next: response => {
        this.accounts = response
      }
    });
  }

  getTransactions() {
    this._service.getAccountTransactions(this.selectedAccount?.id || -1).subscribe({
      next: response => {
        this.transactions = response
      }
    });
  }

  createAccount() {
    this._service.createAccount(this.selectedCustomer?.id || -1, this.initialBalance).subscribe({
      next: response => {
        this.accounts.push(response);
        this.initialBalance = 0;
      }
    });
  }

  getCustomerFullName() {
    return `${this.selectedCustomer?.firstName} ${this.selectedCustomer?.lastName}`
  }

  selectCustomer(customer: ICustomer): void {
    this.selectedCustomer = customer;
    this.getAccounts();
  }

  selectAccount(account: IAccount): void {
    this.selectedAccount = account;
    this.getTransactions();
  }
}
