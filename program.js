let ingredients = [];
let getAll = async() => {
    try{
    let response = await fetch("http://localhost:8080/ingredient/getAll", {"headers":{"Access-Control-Allow-Origin":"http://localhost:8000/Table"}});
    let data = await response.json();
    return data;
    }
    
  catch(error){
    console.error("Error fetching data:", error);
    return null;
  }
}
const PrintData = async () =>{
  ingredients = await getAll();
  ingredients.forEach(ingredient => {
    console.log(ingredient.Name);
  });
}
let createTable = async () =>{
    ingredients = await getAll();
    let rows = document.getElementById("tableBody");
    ingredients.forEach((ingredient,i) =>{
      let row = document.createElement("tr");
      row.classList.add('row');
      row.appendChild(CreateCell(ingredient.id)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.Name)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.Type)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.Price)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.Weight)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.Origin)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.CanCauseAllergy)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.TypeOfAllergy)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.Temperature)).classList.add("ingredients");
      row.appendChild(CreateCell(ingredient.ExpDate)).classList.add("ingredients");
      rows.appendChild(row);
    });
    return rows;
}
let CreateCell = (text) =>{
  const cell = document.createElement("td");
  cell.textContent = text;
  return cell;
}


(async () => {
  await createTable();
  const TableRows = document.querySelectorAll(".row"),
                    searchBar=document.getElementById("searchBar"),
                    TableHeadings = document.querySelectorAll("thead th");
  const searchTable = () =>{
    TableRows.forEach((row,i)=>{
        let tableData = row.textContent.toLowerCase(),
            searchData = searchBar.value.toLowerCase();
            row.classList.toggle('hide',tableData.indexOf(searchData)<0);
      })
    }
  searchBar.addEventListener('input',searchTable);

  TableHeadings.forEach((heading,i)=>{
    let sort_asc = true;
    heading.onclick = () =>{
      TableHeadings.forEach(heading=>{heading.classList.remove('active');
                                      heading.classList.remove('asc')})
      heading.classList.add("active");
      TableRows.forEach(Row =>{
        
      })

      heading.classList.toggle("asc", sort_asc);
      sort_asc = heading.classList.contains('asc')? false:true;
      SortTable(i, sort_asc);
    }
  }
  )
  const SortTable = (column, sort_asc) =>{
    [...TableRows].sort((a,b)=>{
      let FirstRow = a.querySelectorAll(".ingredients")[column].textContent.toLowerCase(),
      SecondRow = b.querySelectorAll(".ingredients")[column].textContent.toLowerCase();

      const firstValue = parseFloat(FirstRow);
      const secondValue = parseFloat(SecondRow);

      if (!isNaN(firstValue) && !isNaN(secondValue)) {
        return sort_asc ? firstValue - secondValue : secondValue - firstValue;
      } else {
        return sort_asc ? FirstRow.localeCompare(SecondRow) : SecondRow.localeCompare(FirstRow);
      }
      }).map(SortedRow => document.querySelector("tbody").appendChild(SortedRow));
    }
  })();




