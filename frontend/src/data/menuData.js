export const menuData = [
  {
    id: 'dashboard',
    name: 'Dashboard',
    children: [
      { id: 'dashboard', name: 'DashBoard', route: '/' }
    ]
  },
  {
    id: 'clientMgmt',
    name: 'Client Management',
    children: [
      { id: 'clientList', name: 'ClientList', route: '/clientMgmt/list' },
      { id: 'clientCreate', name: 'ClientCreate', route: '/clientMgmt/create' }
    ]
  },
  {
    id: 'salesMgmt',
    name: 'Sales Management',
    children: [
      { id: 'salesList', name: 'SalesList', route: '/salesMgmt/list' },
      { id: 'salesCreate', name: 'SalesCreate', route: '/salesMgmt/create' }
    ]
  },
  {
    id: 'dep1',
    name: 'Dep1',
    children: [
      {
        id: 'dep2', name: 'Dep2',
        children: [
          { id: 'dep3', name: 'Dep3', route: '/dep/3' },
        ]
      },
    ]
  }
]
