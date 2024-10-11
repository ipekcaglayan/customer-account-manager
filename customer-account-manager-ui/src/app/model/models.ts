export interface ICustomer {
  id: number;
  firstName: string;
  lastName: string;

}

export interface IAccount {
  id: number;
  customer: ICustomer;
  balance: number;
  createdAt: string;

}

export interface ITransaction {
  id: number;
  accountId: number;
  amount: number;
  createdAt: string;

}
