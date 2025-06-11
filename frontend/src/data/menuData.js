export const menuData = [
  {
    id: 'dashboard',
    name: 'Dashboard',
    children: [
      { id: 'dashboard', name: 'Home', route: '/' }
    ]
  },
  {
    id: 'clientMgmt',
    name: 'Client Management',
    children: [
      {id: 'clientList', name: 'Client List', route: '/clientMgmt/list'},
      {id: 'clientCreate', name: 'Client Create', route: '/clientMgmt/create'}
    ]
  },
  {
    id: 'salesMgmt',
    name: 'Sales Management',
    children: [
      {id: 'salesList', name: 'Sales List', route: '/salesMgmt/list'},
      {id: 'salesCreate', name: 'Sales Create', route: '/salesMgmt/create'}
    ]
  }
]
