ExcutionPlan executionPlan;
Set visited;


buildExecutionPlan(node) {
	visited.add(node);

	foreach child with index i {
		if (visited.contains(child)) {
			// This child is the lowest part of a diamond structure, has been visited by another branch and as such is already part of the plan. Shift all plan-nodes to its right and inject the existing set of END ControlFlow-nodes gathered so far. If node (it's parent) is not the part of the outermost right branch
			k = plan.getPositionOfNode(node);
			plan.injectAtPosition(k+1, endCfNodes);
			continue;
		}
		
		if (i == node.getNumOfChildren()) {
			// Picking up the outermost-right branch.
			// Add (another) END ControlFlow node and pass them all of them to the child
			endCfNodes.append(CF_END_NODE(node.getId()));
			buildExecutionPlan(child, endCfNodes);
		}
		else { // Any intermediate child
			buildExecutionPlan(child, []);
		}
		
		// All downstream branches have been a visited, add a BEGIN ControlFlow-node and then node itself
		endCfNodes.add(CF_BEGIN_NODE(node.getId()));
		plan.append(node);
	}

}


// First, see the topological sorting. Note, the algorithm is returning the nodes in reverse order (
//
// We don't use stack. We use array. The reason is that we need to "inject" END ControlFlow-nodes when arriving at the end of a node-structure. The BEGIN and CP can gracefully be appended at plan array while we traverse the graph.
// We call the method reverseExecutionPlan because the array returned must be read from the end towards start. With a stack we 'd just have to call pop() until empty
//

// At the beginning of the
// The END ControlFlow-nodes are set only at the end of the outer right branch. The node right after is the end of the diamond
//
// At the beginning of the other branches, we add a CHECKPOINT ControlFlow-node

// CHECKPOINT-nodes mark essentially the switch of the branch. It's the point we "jump" to if anything goes wrong in the previous branch
//


// There node might be part of multiple nested diamond structures. Thus, each ControlFlow node carries an identifier that relates it to a single diamond structure.
//

// if a node is part of the right-most () branch
//
/


// Asymptotic complexity is O(n^3). Practically though, algorithm behaves as c^2 * n, c being the diameter of the network. Given the fact that PB graphs are "short and wide" it scales close to linearly
