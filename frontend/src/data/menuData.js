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
      { id: 'clientCreate', name: 'ClientCreate', route: '/clientMgmt/create' },
      { id: 'modalSample01', name: 'modalSample01', route: '/clientMgmt/modalSample01' },
      { id: 'modalSample02', name: 'modalSample02', route: '/clientMgmt/modalSample02' },
      { id: 'modalSample03', name: 'modalSample03', route: '/clientMgmt/modalSample03' },
      { id: 'modalSample04', name: 'modalSample04', route: '/clientMgmt/modalSample04' },
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
