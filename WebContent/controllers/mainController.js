var mainController	 = angular.module('mainController', []);
mainController.controller('homeController',  ['$scope', function($scope, $http) {
  $scope.message = "This page will be used to display Home form";
}]);

mainController.controller('AddMemberController',   function($scope, $http) {
  $scope.message = "This page will be used to display AddMemberController";
  $scope.url="/AllocationDashboard/AddResource";
  $scope.memberdata={"fname":"Ross",
	      "lname":"Geller",
	      "projName":"Hyperloop"};
  $scope.reset=function()
  {
	  //alert("reset Called");
	  //alert($scope.params.fname);
	  $scope.params=angular.copy("");
  }
  $scope.addResource=function(params)
  {
	  $scope.result=false;
	  //alert("AddProject Function Called");
	  $scope.memberdata.fname=params.fname;
	  $scope.memberdata.lname=params.lname;
	  $scope.memberdata.projName=params.projName;
	  var data=JSON.stringify($scope.memberdata);
	  //alert("Data:"+data);
	  var config={
			  headers:{
				  	'Content-Type':'application/x-www-form-urlencoded;charset=utf-8;'
			  }
	  };
	  
	  $http.post($scope.url,data,config).success(function(response){
		  $scope.addmemberresult=response;
		   $scope.result=true;
			
	  });
  }
});

mainController.controller('ViewMemberController' ,  function($scope, $http) {
  $scope.message = "This page will be used to ViewMemberController";
  $scope.message = "This page will be used to display Topbar";
  $scope.url="/AllocationDashboard/ViewResource";
  $scope.memberdata={};
  $scope.getMember=function()
  {
	  //alert("GET Member Function Called");
	  $http.get($scope.url).success(function(response)
			  {
		  		$scope.memberdata=response;
		  		//alert($scope.memberdata.firstname);
			  });
  }
  
  $scope.getMember();
});

mainController.controller('AddProjectController', function($scope, $http) {
	  $scope.message = "This page will be used to AddProjectController";
	  $scope.url="/AllocationDashboard/AddProject";
	  $scope.projectdata={"projectname":"xxxxx",
		      "projectid":"12345",
		      "startdt":"12/31/9999",
		      "enddt":"12/31/9999"};
	  $scope.reset=function()
	  {
		  $scope.params=angular.copy("");
	  }
	  $scope.addProject=function(params)
	  {
		  $scope.result=false;
		  //alert("AddProject Function Called");
		  $scope.projectdata.projectname=params.projectname;
		  $scope.projectdata.projectid=params.projectid;
		  $scope.projectdata.startdt=params.startdt;
		  $scope.projectdata.enddt=params.enddt;
		  $scope.addprojectresult;
		  var data=JSON.stringify($scope.projectdata);
		  //alert("Data:"+data);
		  var config={
				  headers:{
					  	'Content-Type':'application/x-www-form-urlencoded;charset=utf-8;'
				  }
		  };
		  
		  $http.post($scope.url,data,config).success(function(response){
			  $scope.addprojectresult=response;
			   $scope.result=true;
				
		  });
	  }
	  //$scope.addProject();
});

mainController.controller('viewDataController' , function($scope, $http) {
	  $scope.message = "This page will be used to ViewProjectController";
	  $scope.url="/AllocationDashboard/ViewProject";
	  $scope.projdata={};
	  $scope.getProject=function()
	  {
		  //alert("GET Project Function Called");
		  $http.get($scope.url).success(function(response)
				  {
			  		$scope.projdata=response;
			  		//alert($scope.projdata.id);
				  });
	  }
	  
	  $scope.getProject();
	});

mainController.controller('viewAvailController' , function($scope, $http) {
	  $scope.message = "This page will be used to viewAvailController";
	  $scope.url="/AllocationDashboard/ViewAvail";
	  $scope.allocdata={};
	  $scope.getalloc=function()
	  {
		  //alert("GET Project Function Called");
		  $http.get($scope.url).success(function(response)
				  {
			  		$scope.projdata=response;
			  		//alert($scope.projdata.id);
				  });
	  }
	  
	  
	  
	  $scope.getalloc();
	});



        